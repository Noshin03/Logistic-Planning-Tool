package Service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;
import java.io.IOException;
import java.util.ArrayList;

public class ExcelReader {
    private FileInputStream fis;
    private Workbook workbook;
    private Sheet sheet;
    private Integer numberOfVertexes;
    private ArrayList<String> sources;
    private ArrayList<String> targets;
    private ArrayList<Integer> weights;
    private Set<String> vertexes;
    public ExcelReader(String filePath) throws IOException {
        fis = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheetAt(0);
        sources = GetStringColumnByIndex(0);
        targets = GetStringColumnByIndex(1);
        weights = GetNumericColumnByIndex(2);
        numberOfVertexes = CalculateOfVertexes();
    }
    private ArrayList<String> GetStringColumnByIndex(Integer index){
        int totalRows = sheet.getPhysicalNumberOfRows();
        ArrayList<String> result = new ArrayList<String>();
        for (int row = 1; row < totalRows; row++) {
            Row currentRow = sheet.getRow(row);
            Cell cell = currentRow.getCell(index);
            result.add(cell.getStringCellValue());
        }
        return result;
    }

    private ArrayList<Integer> GetNumericColumnByIndex(Integer index){
        int totalRows = sheet.getPhysicalNumberOfRows();
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int row = 1; row < totalRows; row++) {
            Row currentRow = sheet.getRow(row);
            Cell cell = currentRow.getCell(index);
            result.add((int) cell.getNumericCellValue());
        }
        return result;
    }

    private int CalculateOfVertexes(){
        ArrayList<String> rowOne = GetStringColumnByIndex(0);
        ArrayList<String> rowTwo = GetStringColumnByIndex(1);
        ArrayList<String> mergedList = new ArrayList<>(rowOne);
        mergedList.addAll(rowTwo);

        Set<String> uniqueElements = new HashSet<>();
        int numOfVertexes = 0;
        for(String element:mergedList){
            if(uniqueElements.add(element)){
                numOfVertexes += 1;
            }
        }
        this.vertexes = uniqueElements;
        return  numOfVertexes;
    }

    public Integer getNumberOfVertexes() {
        return numberOfVertexes;
    }

    public ArrayList<String> getSources() {
        return sources;
    }

    public ArrayList<String> getTargets() {
        return targets;
    }

    public ArrayList<Integer> getWeights() {
        return weights;
    }

    public Set<String> getVertexes() {
        return vertexes;
    }

    public int findIndexOfElement(String element) {
        int index = 0;
        for (String setElement : vertexes) {
            if (setElement.equals(element)) {
                return index;
            }
            index++;
        }
        return -1; // Element not found
    }
}
