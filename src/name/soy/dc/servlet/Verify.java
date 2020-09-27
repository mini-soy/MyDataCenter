package name.soy.dc.servlet;


import name.soy.dc.DataCenter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/verify")
public class Verify extends HttpServlet {
    public static boolean isValid(HttpServletRequest req) {
        if(DataCenter.center==null)
            return false;
        else
            return true;
    }

    /**
     *
     * @param req
     * switch(@return)
     * 0=>sucess
     * 1=>failed
     * -1=>server failed
     *
     */
    public static int validcode(HttpServletRequest req) {
        return 0;
    }
    public static boolean validcode(String code){
        return true;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
