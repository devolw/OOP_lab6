import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Объявление сервлета и его маппинг на URL "/temperature"
@WebServlet({"/temperature"})

// Аннотация для указания, что данный сервлет поддерживает загрузку файлов
@MultipartConfig
public class TemperatureServlet extends HttpServlet {
    public TemperatureServlet() {
    }

    // Метод для обработки POST-запроса, отправленного на URL "/temperature"
    protected void doPost(HttpServletRequest var1, HttpServletResponse var2) throws ServletException, IOException {
        // Получение загруженного файла из запроса
        Part var3 = var1.getPart("file");

        // Создание BufferedReader для чтения файла построчно
        BufferedReader var4 = new BufferedReader(new InputStreamReader(var3.getInputStream()));

        // Создание списка для хранения температур
        ArrayList<Integer> var5 = new ArrayList<>();

        String var6;
        int var7;
        int sum = 0;

        // Чтение каждой строки файла и добавление температуры в список var5
        while((var6 = var4.readLine()) != null) {
            var7 = Integer.parseInt(var6.trim());
            var5.add(var7);
            sum += var7;
        }

        // Вычисление среднемесячной температуры
        int var8 = var5.size();
        int var9 = sum / var8;

        // Вычисление количества дней, когда температура была выше/ниже среднемесячной
        int var10 = (int)var5.stream().filter((var2x) -> {
            return var2x > var9;
        }).count();
        int var11 = (int)var5.stream().filter((var2x) -> {
            return var2x < var9;
        }).count();

        // Получение списка топ-3 температур
        Collections.sort(var5, Collections.reverseOrder());
        List<Integer> var12 = var5.subList(0, Math.min(3, var5.size()));

        // Установка атрибутов запроса для передачи данных на страницу index.jsp
        var1.setAttribute("averageTemperature", var9);
        var1.setAttribute("daysAboveAverage", var10);
        var1.setAttribute("daysBelowAverage", var11);
        var1.setAttribute("topTemperatures", var12);

        // Перенаправление запроса на страницу index.jsp
        RequestDispatcher var13 = var1.getRequestDispatcher("index.jsp");
        var13.forward(var1, var2);
    }
}
