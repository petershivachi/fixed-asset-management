package com.fixed.assets.app.fixedassets.Utils;

import com.fixed.assets.app.fixedassets.Models.Asset.Asset;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Excel {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static boolean hasExcelFormat(MultipartFile file){
       return  TYPE.equals(file.getContentType());
    }

    public static List<Asset> excelToAssets (InputStream is){
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheetAt(0);
            Sheet sheet1 = workbook.getSheetAt(1);
            Sheet sheet2 = workbook.getSheetAt(2);
            Sheet sheet3 = workbook.getSheetAt(3);
            Sheet sheet4 = workbook.getSheetAt(4);
            Sheet sheet5 = workbook.getSheetAt(5);
            Sheet sheet6 = workbook.getSheetAt(6);
            Sheet sheet7 = workbook.getSheetAt(7);
            Sheet sheet8 = workbook.getSheetAt(8);

            Iterator<Row> rows = sheet.iterator();
            Row headerRow = rows.next();
            List<Asset> assets = new ArrayList<Asset>();
            int rowNumber = 2;

            while(rows.hasNext()){
                Row currentRow = rows.next();
                //Skip header
                if(rowNumber == 2){
                    rowNumber++;
                    continue;
            }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
                int cellIndex = 0;

                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex){
                        case 0:
                            if(currentCell.getCellType()==CellType.STRING){
                                asset.setLrNo(currentCell.getStringCellValue());
                            }else if (currentCell.getCellType() == CellType.NUMERIC){
                                asset.setLrNo(String.valueOf(currentCell.getNumericCellValue()));
                            }
                            break;
                        case 1:
                            if (currentCell.getCellType() == CellType.STRING){
                                asset.setSize(Double.valueOf(currentCell.getStringCellValue()));
                            }else  if(currentCell.getCellType() == CellType.NUMERIC){
                                asset.setSize(currentCell.getNumericCellValue());
                            }
                            break;
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
                            asset.setCustodian(currentCell.getStringCellValue());
                            break;
                        case 8:
                            asset.setLocation(currentCell.getStringCellValue());
                            break;
                        case 9:
                            asset.setRemarks(currentCell.getStringCellValue());
                            break;
                        case 10:
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
            Iterator<Row> sheet1Rows = sheet1.iterator();
            Row sheet1HeaderRow = sheet1Rows.next();
            int sheet1RowNumber = 3;

             while (sheet1Rows.hasNext()){
                 Row currentRow = sheet1Rows.next();
                 //Skip header
                  if(sheet1RowNumber == 3){
                     sheet1RowNumber++;
                     continue;
                 }

                 Iterator<Cell> cellsInRow = currentRow.iterator();
                 Asset asset = new Asset();
                 int cellIndex = 0;

                 while (cellsInRow.hasNext()){
                     Cell currentCell = cellsInRow.next();
                     switch (cellIndex){
                         case 0:
                             asset.setLrNo(currentCell.getStringCellValue());
                             break;
                         case 1:
                             if(currentCell.getCellType() == CellType.NUMERIC){
                                 asset.setSize(currentCell.getNumericCellValue());
                             }else if(currentCell.getCellType() ==
                                CellType.STRING){
                                 asset.setSize(Double.valueOf(currentCell.getStringCellValue()));
                             }
                             break;
                         case 2:
                             asset.setLocalAuthority(currentCell.getStringCellValue());
                             break;
                         case 3:
                             asset.setName(currentCell.getStringCellValue());
                             break;
                         case 4:
                             asset.setCost(currentCell.getNumericCellValue());
                             break;
                         case  5:
                             asset.setDateAcquired(currentCell.getDateCellValue());
                             break;
                         case 6:
                             asset.setDepreciationType(currentCell.getStringCellValue());
                             break;
                         case 7:
                             asset.setDepreciationRate(currentCell.getNumericCellValue());
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

             //Iteration for the third sheet
             Iterator<Row> sheet2Rows = sheet2.iterator();
             Row sheet2HeaderRow = sheet2Rows.next();
             int sheet2RowNumber = 2;

            while (sheet2Rows.hasNext()){
                Row currentRow = sheet2Rows.next();
                //Skip header
                if(sheet2RowNumber == 2){
                    sheet2RowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
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
                            asset.setCost(currentCell.getNumericCellValue());
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
            Iterator<Row> sheet3Rows = sheet3.iterator();
            Row sheet3HeaderRow = sheet3Rows.next();
            int sheet3RowNumber = 2;

            while (sheet3Rows.hasNext()){
                Row currentRow = sheet3Rows.next();

                //Skip header
                if(sheet3RowNumber == 2){
                    sheet3RowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
                int cellIndex = 0;

                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex){
                        case 0:
                            asset.setSerialNumber(currentCell.getStringCellValue());
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
                            if (currentCell.getCellType() == CellType.NUMERIC){
                                asset.setCost(currentCell.getNumericCellValue());
                            }else if(currentCell.getCellType() == CellType.STRING){
                                asset.setCost(Double.valueOf(currentCell.getStringCellValue()));
                            }
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
            Iterator<Row> sheet4Rows = sheet4.iterator();
            Row sheet4HeaderRow = sheet4Rows.next();
            int sheet4RowNumber = 2;

            while (sheet4Rows.hasNext()){
                Row currentRow = sheet4Rows.next();

                if(sheet4RowNumber == 2){
                    sheet4RowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
                int cellIndex = 0;

                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex){
                        case 0:
                            asset.setSerialNumber(currentCell.getStringCellValue());
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
                            asset.setDepreciationRate(currentCell.getNumericCellValue());
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
            Iterator<Row> sheet5Rows = sheet5.iterator();
            Row sheet5HeaderRows = sheet5Rows.next();
            int sheet5RowNumber = 2;

            while (sheet5Rows.hasNext()){
                Row currentRow = sheet5Rows.next();

                if(sheet5RowNumber == 2){
                    sheet5RowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
                int cellIndex = 0;

                    while (cellsInRow.hasNext()){
                        Cell currentCell = cellsInRow.next();
                        switch (cellIndex){
                            case 0:
                                asset.setSerialNumber(currentCell.getStringCellValue());
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
            Iterator<Row> sheet6Rows = sheet6.iterator();
            Row sheet6HeaderRow = sheet6Rows.next();
            int sheet6RowNumber = 2;

            while (sheet6Rows.hasNext()){
                Row currentRow = sheet6Rows.next();

                if(sheet6RowNumber == 2){
                    sheet6RowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
                int cellIndex = 0;

                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex){
                        case 0:
                            if(currentCell.getCellType() == CellType.NUMERIC){
                                asset.setSerialNumber(String.valueOf(currentCell.getNumericCellValue()));
                            }else if (currentCell.getCellType() == CellType.STRING){
                                asset.setSerialNumber(currentCell.getStringCellValue());
                            }
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
            Iterator<Row> sheet7Rows = sheet7.iterator();
            Row sheet7HeaderRow = sheet7Rows.next();
            int sheet7RowNumber = 2;

            while (sheet7Rows.hasNext()){
                Row currentRow = sheet7Rows.next();

                if(sheet7RowNumber == 2){
                    sheet7RowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
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
            Iterator<Row> sheet8Rows = sheet8.iterator();
            Row sheet8HeaderRow = sheet8Rows.next();
            int sheet8RowNumber = 2;

            while (sheet8Rows.hasNext()){
                Row currentRow = sheet8Rows.next();

                if(sheet8RowNumber == 2){
                    sheet8RowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();
                Asset asset = new Asset();
                int cellIndex = 0;

                while (cellsInRow.hasNext()){
                    Cell currentCell = cellsInRow.next();
                    switch (cellIndex){
                        case 0:
                            asset.setSerialNumber(currentCell.getStringCellValue());
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
