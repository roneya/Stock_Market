package com.example.StockMarket;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.mail.javamail.MimeMessageHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    StocksRepository stocksRepository;
    @Autowired
    JavaMailSender javaMailSender;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }



    public String addUser(User user) throws Exception {
        try{
            User existingUser = userRepository.findById(user.getEmail()).get();
        }
        catch (Exception e){

           user.setPassword(passwordEncoder.encode(user.getPassword()));

           userRepository.save(user);
           return "Done";
        }
        return "Email already in use";
    }

    public String auth(Login login) {

        User user = userRepository.findById(login.getEmail()).get();
        if (user == null) {
            return "false";
        }
        if(passwordEncoder.matches(login.getPassword(), user.getPassword())) return "Successful login";
        return "Failed";

    }


    private static final String API_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_{frq}&symbol={symbol}&apikey={apikey}";

    public String getInfo(Stocks stocks) throws MessagingException {
        String key = "ND0M9WQI5K7TN5KE";
        RestTemplate restTemplate = new RestTemplate();
        List<String> ans = new ArrayList<>();
        for(int i =0 ;i<stocks.getStock_symbol().size();i++){
            String smb = stocks.getNotificationFrequency().get(i).toString();
            String apiUrl = API_URL.replace("{symbol}", stocks.getStock_symbol().get(i)).replace("{frq}", smb).replace("{apikey}", key);

            String response = restTemplate.getForObject(apiUrl, String.class);
            String cd = smb.substring(0, 1).toUpperCase() + smb.substring(1).toLowerCase();
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(response, JsonObject.class);
            JsonObject monthlyTimeSeries = jsonObject.getAsJsonObject(cd+" Time Series");
            String firstDate = monthlyTimeSeries.keySet().iterator().next();
            JsonObject firstData = monthlyTimeSeries.getAsJsonObject(firstDate);
            String open = firstData.get("1. open").getAsString();
            String high = firstData.get("2. high").getAsString();
            String low = firstData.get("3. low").getAsString();
            String close = firstData.get("4. close").getAsString();
            String volume = firstData.get("5. volume").getAsString();

            List<String> firstDataList = Arrays.asList(stocks.getStock_symbol().get(i),smb,firstDate, open, high, low, close, volume);
            //firstDataList.add(0,smb); //stock name
            //open, high, low, close, volume
            ans.add(firstDataList.toString()); //final answer
        }

        sendStockDetailsEmailAtTime(stocks.getEmail(), ans.toString());

        stocksRepository.save(stocks);
        Session session = new Session();
        session.setEmail(stocks.getEmail());
        session.setInfo(ans); //information saved in session
        sessionRepository.save(session);
        return ans.toString();
    }
    public void sendStockDetailsEmailAtTime( String recipient, String emailBody) throws MessagingException {

        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
        mimeMessageHelper.setFrom("nerdartist47@gmail.com");
        mimeMessageHelper.setTo(recipient);
        mimeMessageHelper.setText(emailBody);
        mimeMessageHelper.setSubject("Stock details");

        javaMailSender.send(mimeMessage);



    }




    //below 4.Allow users to view stock details for the below given inputs


    public String abc( InfoInRange infoInRange){
        String key = "ND0M9WQI5K7TN5KE";
        RestTemplate restTemplate = new RestTemplate();
            String smb = "WEEKLY"; //as daily is premium

            String apiUrl = API_URL.replace("{symbol}",infoInRange.getStock_symbol()).replace("{frq}", smb).replace("{apikey}", key);

            String response = restTemplate.getForObject(apiUrl, String.class);
        List<String> dataList = new ArrayList<>();
        String start = infoInRange.getStart_date();
        String end = infoInRange.getEnd_date();

        Gson gson = new Gson();

        try {
            JsonElement jsonElement = JsonParser.parseString(response);

            JsonObject jsonObject = jsonElement.getAsJsonObject();


            JsonObject timeSeries = jsonObject.getAsJsonObject("Weekly Time Series");
            //tried for weekly but its premium api we have to do it with weekly




            for (Map.Entry<String, JsonElement> entry : timeSeries.entrySet()) {
                String date = entry.getKey();

                if (date.compareTo(start) >= 0 && date.compareTo(end) <= 0) {
                    JsonObject data = entry.getValue().getAsJsonObject();

                    String row = " [ "+ date + "," + data.get("1. open") + "," + data.get("2. high") + "," + data.get("3. low") + "," + data.get("4. close") + "," + data.get("5. volume")+" ] ";
                    dataList.add(row);
                }
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return dataList.toString();
    }



}