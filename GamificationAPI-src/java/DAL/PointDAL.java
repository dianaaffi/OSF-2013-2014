/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

import entities.Point;
import java.util.List;
import util.HibernateUtil;

/**
 *
 * @author user
 */
public class PointDAL {

    private static final String GET_ALL_POINTS_QUERY = "from Point as point where point.application =";
    private static final String GET_POINT_BY_ID_QUERY = "from Point as point where point.id =";

    public static List<Point> getAllPointsByApplicationId(int applicationId) {
        List<Point> allPoints = null;
        try {
            allPoints = HibernateUtil.executeHQLQuery(GET_ALL_POINTS_QUERY + applicationId);
        } catch (Exception ex) {
            System.out.println("Error in PointDAL-getAllPointsByApplicationId: " + ex.getMessage());
        }
        return allPoints;
    }

    public static Point getPointById(int pointId) {
        List<Point> allPoints;
        Point point = null;
        try {
            allPoints = HibernateUtil.executeHQLQuery(GET_POINT_BY_ID_QUERY + pointId);
            if (allPoints != null && !allPoints.isEmpty()) {
                point = allPoints.get(0);
            }
        } catch (Exception ex) {
            System.out.println("Error in PointDAL-getPointById: " + ex.getMessage());
        }
        return point;
    }

    public static Point addPoint(Point point) {
        try {
            point.setId(HibernateUtil.addEntity(point));
        } catch (Exception ex) {
            System.out.println("Error in PointDAL-addPoint: " + ex.getMessage());
        }
        return point;
    }

    public static void editPoint(Point point) {
        try {
            if (point.getId() != null && point.getId() > 0) {
                HibernateUtil.editEntity(point);
            }
        } catch (Exception ex) {
            System.out.println("Error in PointDAL-editPoint: " + ex.getMessage());
        }
    }
    
    public static void deletePoint(Point point) {
        try {
            if (point.getId() != null && point.getId() > 0) {
                HibernateUtil.deleteEntity(point);
            }
        } catch (Exception ex) {
            System.out.println("Error in PointDAL-deletePoint: " + ex.getMessage());
        }
    }

}
