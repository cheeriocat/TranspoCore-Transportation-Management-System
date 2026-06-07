package dao;

import Dbconnection.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShipmentReportDB {

    public static List<Object[]> getMonthlyShipmentVolumes() {
        List<Object[]> list = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement( 
                "SELECT YEAR(shipmentDate) AS year, MONTH(shipmentDate) AS month, COUNT(*) AS total_shipments FROM shipments GROUP BY YEAR(shipmentDate), MONTH(shipmentDate) ORDER BY year DESC, month DESC")) {

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("year"),
                    rs.getInt("month"),
                    rs.getInt("total_shipments")
                };
                list.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
