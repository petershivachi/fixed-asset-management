package com.fixed.assets.app.fixedassets.Utils;

import com.fixed.assets.app.fixedassets.Models.Asset.Asset;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Excel {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public String[] HEADERS = {"LR No.", "Size", "Name", "Cost", "Date Acquired(MM/DD/YYYY)", "Depreciation Type", "Depreciation Rate", "Custodian",
    "Location (Ward)", "Remarks", "Local Authority", "Department Unit", "Reg No", "Engine No", "Chassis No", "Serial Number", "Make", "Model", "Type"};
    static String SHEET = "Assets";

    public static boolean hasExcelFormat(MultipartFile file){
       return  TYPE.equals(file.getContentType());
    }

    public static List<Asset> excelToAssets (InputStream is){
        try {
            Workbook workbook = new XSSFWorkbook(is);

            //Iteration for the first worksheet
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rows = sheet.iterator();
            List<Asset> assets = new ArrayList<Asset>();
            int rowNumber = 1;

            while(rows.hasNext()){
                Row currentRow = rows.next();
                //Skip header
                if(rowNumber == 1){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
               // asset.setCategoryCode(categoryCode);
                int cellIndex = 0;

                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex){
                        case 0:
                            asset.setLrNo(currentCell.getStringCellValue());
                            break;
                        case 1:
                            //asset.setSize((long) currentCell.getNumericCellValue());
                            continue;
                        case 2:
                            asset.setName(currentCell.getStringCellValue());
                            break;
                        case 3:
                            asset.setCost((double)currentCell.getNumericCellValue());
                            break;
                        case 4:
                            asset.setDateAcquired(currentCell.getDateCellValue());
                            break;
                        case  5:
                            asset.setDepreciationType(currentCell.getStringCellValue());
                            break;
                        case 6:
                            asset.setDepreciationRate((double)currentCell.getNumericCellValue());
                            break;
                        case 7:
                            asset.setLocation(currentCell.getStringCellValue());
                            break;
                        case 8:
                            asset.setRemarks(currentCell.getStringCellValue());
                            break;
                        case 9:
                            asset.setDepartmentUnit(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                assets.add(asset);
            }

            //Iteration for the second worksheet
            Sheet sheet1 = workbook.getSheetAt(1);
             rows = sheet1.iterator();

             while (rows.hasNext()){
                 Row currentRow = rows.next();

                 //Skip header
                 if(rowNumber == 0){
                     rowNumber++;
                     continue;
                 }

                 Iterator<Cell> cellsInRow = currentRow.iterator();
                 Asset asset = new Asset();
                 //asset.setCategoryCode(categoryCode);
                 int cellIndex = 0;

                 while (cellsInRow.hasNext()){
                     Cell currentCell = cellsInRow.next();
                     switch (cellIndex){
                         case 0:
                             asset.setLrNo(currentCell.getStringCellValue());
                             break;
                         case 1:
                             //asset.setSize(currentCell.getNumericCellValue());
                             continue;
                             //break;
                         case 2:
                             asset.setLocalAuthority(currentCell.getStringCellValue());
                             break;
                         case 3:
                             asset.setName(currentCell.getStringCellValue());
                             break;
                         case 4:
                             asset.setCost((double) currentCell.getNumericCellValue());
                             break;
                         case  5:
                             asset.setDateAcquired(currentCell.getDateCellValue());
                             break;
                         case 6:
                             asset.setDepreciationType(currentCell.getStringCellValue());
                             break;
                         case 7:
                             asset.setDepreciationRate((double) currentCell.getNumericCellValue());
                             break;
                         case 8:
                             asset.setCustodian(currentCell.getStringCellValue());
                             break;
                         case 9:
                             asset.setLocation(currentCell.getStringCellValue());
                             break;
                         case 10:
                             asset.setRemarks(currentCell.getStringCellValue());
                             break;
                         case 11:
                             asset.setDepartmentUnit(currentCell.getStringCellValue());
                             break;
                         default:
                             break;
                     }
                     cellIndex++;
                 }
                 assets.add(asset);
             }

             //CIteration for the third sheet
            Sheet sheet2 = workbook.getSheetAt(2);
             rows = sheet2.iterator();

            while (rows.hasNext()){
                Row currentRow = rows.next();

                //Skip header
                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
                //asset.setCategoryCode(categoryCode);
                int cellIndex = 0;

                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex){
                        case 0:
                            asset.setRegistrationNumber(currentCell.getStringCellValue());
                            break;
                        case 1:
                            asset.setEngineNumber(currentCell.getStringCellValue());
                            break;
                        case 2:
                            asset.setChassisNumber(currentCell.getStringCellValue());
                            break;
                        case 3:
                            asset.setLocalAuthority(currentCell.getStringCellValue());
                            break;
                        case 4:
                            asset.setName(currentCell.getStringCellValue());
                            break;
                        case  5:
                            asset.setCost((double) currentCell.getNumericCellValue());
                            break;
                        case 6:
                            asset.setDateAcquired(currentCell.getDateCellValue());
                            break;
                        case 7:
                            asset.setDepreciationType(currentCell.getStringCellValue());
                            break;
                        case 8:
                            asset.setDepreciationRate((double) currentCell.getNumericCellValue());
                            break;
                        case 9:
                            asset.setCustodian(currentCell.getStringCellValue());
                            break;
                        case 10:
                            asset.setLocation(currentCell.getStringCellValue());
                            break;
                        case 11:
                            asset.setRemarks(currentCell.getStringCellValue());
                            break;
                        case 12:
                            asset.setDepartmentUnit(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                assets.add(asset);
            }

            //Iteration for the fourth sheet
            Sheet sheet3 = workbook.getSheetAt(3);
            rows = sheet3.iterator();

            while (rows.hasNext()){
                Row currentRow = rows.next();

                //Skip header
                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
                //asset.setCategoryCode(categoryCode);
                int cellIndex = 0;

                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex){
                        case 0:
                            asset.setSerialNumber((long) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            asset.setMake(currentCell.getStringCellValue());
                           break;
                        case 2:
                            asset.setModel(currentCell.getStringCellValue());
                            break;
                        case 3:
                            asset.setName(currentCell.getStringCellValue());
                            break;
                        case 4:
                            asset.setCost((double) currentCell.getNumericCellValue());
                            break;
                        case  5:
                            asset.setDateAcquired(currentCell.getDateCellValue());
                            break;
                        case 6:
                            asset.setDepreciationType(currentCell.getStringCellValue());
                            break;
                        case 7:
                            asset.setDepreciationRate((double) currentCell.getNumericCellValue());
                            break;
                        case 8:
                            asset.setCustodian(currentCell.getStringCellValue());
                            break;
                        case 9:
                            asset.setLocation(currentCell.getStringCellValue());
                            break;
                        case 10:
                            asset.setRemarks(currentCell.getStringCellValue());
                            break;
                        case 11:
                            asset.setDepartmentUnit(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                assets.add(asset);
            }

            //Cell iteration for worksheet five
            Sheet sheet4 = workbook.getSheetAt(4);
            rows = sheet4.iterator();

            while (rows.hasNext()){
                Row currentRow = rows.next();

                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
                //asset.setCategoryCode(categoryCode);
                int cellIndex = 0;

                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex){
                        case 0:
                            asset.setSerialNumber((long) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            asset.setMake(currentCell.getStringCellValue());
                            break;
                        case 2:
                            asset.setModel(currentCell.getStringCellValue());
                            break;
                        case 3:
                            asset.setName(currentCell.getStringCellValue());
                            break;
                        case 4:
                            asset.setCost((double) currentCell.getNumericCellValue());
                            break;
                        case  5:
                            asset.setDateAcquired(currentCell.getDateCellValue());
                            break;
                        case 6:
                            asset.setDepreciationType(currentCell.getStringCellValue());
                            break;
                        case 7:
                            asset.setDepreciationRate((double) currentCell.getNumericCellValue());
                            break;
                        case 8:
                            asset.setCustodian(currentCell.getStringCellValue());
                            break;
                        case 9:
                            asset.setLocation(currentCell.getStringCellValue());
                            break;
                        case 10:
                            asset.setRemarks(currentCell.getStringCellValue());
                            break;
                        case 11:
                            asset.setDepartmentUnit(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                assets.add(asset);
            }

            //Iteration for worksheet six
            Sheet sheet5 = workbook.getSheetAt(5);
            rows = sheet5.iterator();

            while (rows.hasNext()){
                Row currentRow = rows.next();

                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
                //asset.setCategoryCode(categoryCode);
                int cellIndex = 0;

                    while (cellsInRow.hasNext()){
                        Cell currentCell = cellsInRow.next();
                        switch (cellIndex){
                            case 0:
                                asset.setSerialNumber((long) currentCell.getNumericCellValue());
                                break;
                            case 1:
                                asset.setType(currentCell.getStringCellValue());
                                break;
                            case 2:
                                asset.setName(currentCell.getStringCellValue());
                                break;
                            case 3:
                                asset.setCost((double) currentCell.getNumericCellValue());
                                break;
                            case 4:
                                asset.setDateAcquired(currentCell.getDateCellValue());
                                break;
                            case  5:
                                asset.setDepreciationType(currentCell.getStringCellValue());
                                break;
                            case 6:
                                asset.setDepreciationRate((double) currentCell.getNumericCellValue());
                                break;
                            case 7:
                                asset.setCustodian(currentCell.getStringCellValue());
                                break;
                            case 9:
                                asset.setLocation(currentCell.getStringCellValue());
                                break;
                            case 10:
                                asset.setRemarks(currentCell.getStringCellValue());
                                break;
                            case 11:
                                asset.setDepartmentUnit(currentCell.getStringCellValue());
                                break;
                            default:
                                break;
                        }
                        cellIndex++;
                    }
                    assets.add(asset);
            }

            //Iteration for worksheet seven
            Sheet sheet6 = workbook.getSheetAt(6);
            rows = sheet6.iterator();

            while (rows.hasNext()){
                Row currentRow = rows.next();

                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
                //asset.setCategoryCode(categoryCode);
                int cellIndex = 0;

                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex){
                        case 0:
                            asset.setSerialNumber((long) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            asset.setType(currentCell.getStringCellValue());
                            break;
                        case 2:
                            asset.setName(currentCell.getStringCellValue());
                            break;
                        case 3:
                            asset.setCost((double) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            asset.setDateAcquired(currentCell.getDateCellValue());
                            break;
                        case  5:
                            asset.setDepreciationType(currentCell.getStringCellValue());
                            break;
                        case 6:
                            asset.setDepreciationRate((double) currentCell.getNumericCellValue());
                            break;
                        case 7:
                            asset.setCustodian(currentCell.getStringCellValue());
                            break;
                        case 9:
                            asset.setLocation(currentCell.getStringCellValue());
                            break;
                        case 10:
                            asset.setRemarks(currentCell.getStringCellValue());
                            break;
                        case 11:
                            asset.setDepartmentUnit(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                assets.add(asset);
            }

            //Iteration for worksheet eight
            Sheet sheet7 = workbook.getSheetAt(7);
            rows = sheet7.iterator();

            while (rows.hasNext()){
                Row currentRow = rows.next();

                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
                //asset.setCategoryCode(categoryCode);
                int cellIndex = 0;

                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex){
                        case 0:
                            asset.setType(currentCell.getStringCellValue());
                            break;
                        case 1:
                            asset.setName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            asset.setCost((double) currentCell.getNumericCellValue());
                            break;
                        case 3:
                            asset.setDateAcquired(currentCell.getDateCellValue());
                            break;
                        case  4:
                            asset.setDepreciationType(currentCell.getStringCellValue());
                            break;
                        case 5:
                            asset.setDepreciationRate((double) currentCell.getNumericCellValue());
                            break;
                        case 6:
                            asset.setCustodian(currentCell.getStringCellValue());
                            break;
                        case 7:
                            asset.setLocation(currentCell.getStringCellValue());
                            break;
                        case 8:
                            asset.setRemarks(currentCell.getStringCellValue());
                            break;
                        case 9:
                            asset.setDepartmentUnit(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                assets.add(asset);
            }

            //Iteration for worksheet nine
            Sheet sheet8 = workbook.getSheetAt(8);
            rows = sheet8.iterator();

            while (rows.hasNext()){
                Row currentRow = rows.next();

                if(rowNumber == 0){
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
                //asset.setCategoryCode(categoryCode);
                int cellIndex = 0;

                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex){
                        case 0:
                            asset.setSerialNumber((long) currentCell.getNumericCellValue());
                            break;
                        case 1:
                            asset.setType(currentCell.getStringCellValue());
                            break;
                        case 2:
                            asset.setName(currentCell.getStringCellValue());
                            break;
                        case 3:
                            asset.setCost((double) currentCell.getNumericCellValue());
                            break;
                        case 4:
                            asset.setDateAcquired(currentCell.getDateCellValue());
                            break;
                        case  5:
                            asset.setDepreciationType(currentCell.getStringCellValue());
                            break;
                        case 6:
                            asset.setDepreciationRate((double) currentCell.getNumericCellValue());
                            break;
                        case 7:
                            asset.setCustodian(currentCell.getStringCellValue());
                            break;
                        case 9:
                            asset.setLocation(currentCell.getStringCellValue());
                            break;
                        case 10:
                            asset.setRemarks(currentCell.getStringCellValue());
                            break;
                        case 11:
                            asset.setDepartmentUnit(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIndex++;
                }
                assets.add(asset);
            }

            workbook.close();

            return assets;
        }catch (IOException e){
            throw new RuntimeException("Fail to parse Excel file: "+e.getMessage());
        }
    }
}
