import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelDataExtractor {
    private final XSSFWorkbook workbook;
    private final String sheetName;
    private final String testNameColumn;

    public ExcelDataExtractor(String filePath, String sheetName, String testNameColumn) throws IOException {
        FileInputStream fis = new FileInputStream(filePath);
        this.workbook = new XSSFWorkbook(fis);
        this.sheetName = sheetName;
        this.testNameColumn = testNameColumn;
    }

    public ArrayList<String> getData(String testName) throws IOException {
        ArrayList<String> valueToAssert = new ArrayList<>();
        int sheets = workbook.getNumberOfSheets();

        for (int i = 0; i < sheets; i++) {
            if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
                XSSFSheet sheet = workbook.getSheetAt(i);
                Iterator<Row> rows = sheet.iterator();
                Row firstrow = rows.next();
                Iterator<Cell> cellIterator = firstrow.cellIterator();
                int k = 0;
                int column = 0;
                while (cellIterator.hasNext()) {
                    Cell value = cellIterator.next();
                    if (value.getStringCellValue().equalsIgnoreCase(testNameColumn)) {
                        column = k;
                    }
                    k++;
                }
                while (rows.hasNext()) {
                    Row row = rows.next();
                    if (row.getCell(column).getStringCellValue().equalsIgnoreCase(testName)) {
                        Iterator<Cell> cellValueIterator = row.cellIterator();
                        while (cellValueIterator.hasNext()) {
                            Cell cell = cellValueIterator.next();
                            if (cell.getCellType() == CellType.STRING) {
                                valueToAssert.add(cellValueIterator.next().getStringCellValue());
                            } else {
                                valueToAssert.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
                            }
                        }
                    }
                }
            }
        }
        return valueToAssert;
    }

}