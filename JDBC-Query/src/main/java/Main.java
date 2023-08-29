import rikkei.academy.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class Main {
    public static void main(String[] args) {
        Connection conn = ConnectDB.getConnection();
        System.out.println(conn);

//        CallableStatement callSt = null;
        CallableStatement callSt = null;
        try {
            callSt = conn.prepareCall("{call call_avg_age(?)}");
            callSt.registerOutParameter(1, Types.DOUBLE);
            callSt.executeQuery();
            double out = callSt.getDouble(1);
            System.out.println(out);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            if(callSt != null){
                try {
                    callSt.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
