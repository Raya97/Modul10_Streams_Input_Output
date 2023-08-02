import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("User.txt"));

        List<User> users = new ArrayList<>();
        String line = reader.readLine(); // Пропускаем заголовок
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            users.add(new User(parts[0], Integer.parseInt(parts[1])));
        }
        reader.close();

        JSONArray userList = new JSONArray();
        for (User user : users) {
            JSONObject userDetails = new JSONObject();
            userDetails.put("name", user.getName());
            userDetails.put("age", user.getAge());
            userList.add(userDetails);
        }

        try (FileWriter file = new FileWriter("User.json")) {
            file.write(userList.toJSONString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
