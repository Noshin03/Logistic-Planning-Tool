package controller;

import Service.DijkstraShortestPath;
import Service.ExcelReader;

import java.util.ArrayList;
import java.util.List;

public class ApplicationController {
    private DijkstraShortestPath pathFinder;
    private ExcelReader reader;
    public void GenerateGraphWithExcelFile(String filePath){
        try {
            reader = new ExcelReader(filePath);
            pathFinder = new DijkstraShortestPath(reader.getNumberOfVertexes());
            for(int i = 0;i<reader.getSources().size();i++){
                String source = reader.getSources().get(i);
                String target = reader.getTargets().get(i);
                Integer weight = reader.getWeights().get(i);
                pathFinder.addEdge(reader.findIndexOfElement(source),reader.findIndexOfElement(target),weight);
            }
        }catch (Exception e){
            throw new IllegalArgumentException("file not found or invalid my G");
        }
    }

    public String GenerateShortestPath(String source, String target){
        if(pathFinder == null || reader == null){
            throw new IllegalArgumentException("add file first, you don't say");
        }
        int sourceIndex = reader.findIndexOfElement(source);
        int targetIndex = reader.findIndexOfElement(target);
        if(sourceIndex == -1 || targetIndex == -1){
            throw new IllegalArgumentException("invalid source or target");
        }
        List<Integer> path = pathFinder.dijkstraShortestPath(sourceIndex,targetIndex);
        List<String> pathInString = new ArrayList<>();
        List<String> list = new ArrayList<>(reader.getVertexes());
        for(Integer elements:path){
            pathInString.add(list.get(elements));
        }
        return pathInString.toString();
    }
}
