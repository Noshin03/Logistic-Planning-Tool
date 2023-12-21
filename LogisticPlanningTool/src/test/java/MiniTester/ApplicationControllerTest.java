package MiniTester;

import controller.ApplicationController;

public class ApplicationControllerTest {
    public static void main(String[] args){
        ApplicationController controller = new ApplicationController();
        controller.GenerateGraphWithExcelFile("/Users/kaifan/Downloads/TestMap(1000).xlsx");
        System.out.println(controller.GenerateShortestPath("v3","v7"));
    }
}
