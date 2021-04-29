package ru.misterparser.nanopoolstats.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.misterparser.nanopoolstats.entity.RewardStatsByDay;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelServiceImpl implements ExcelService {

    private static final Logger log = LoggerFactory.getLogger(ExcelServiceImpl.class);

    @Override
    public void export(List<RewardStatsByDay> rewardStatsByDays) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            XSSFSheet sheet = workbook.createSheet("Stats");

            int currentRow = 0;
            Row header = sheet.createRow(currentRow++);

            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("Day");

            headerCell = header.createCell(1);
            headerCell.setCellValue("Sum");

            for (RewardStatsByDay rewardStatsByDay : rewardStatsByDays) {
                Row row = sheet.createRow(currentRow++);

                Cell cell0 = row.createCell(0);
                cell0.setCellValue(rewardStatsByDay.getDay());

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(rewardStatsByDay.getSum().doubleValue());
            }

            draw(sheet);

            String filename = "stats.xlsx";
            FileOutputStream outputStream = new FileOutputStream(filename);
            workbook.write(outputStream);
            workbook.close();
            log.debug("Exported {} lines to {}", rewardStatsByDays.size(), filename);

        } catch (Exception e) {
            log.debug("Exception", e);
        }
    }

    private void draw(XSSFSheet sheet) {
        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 3, 1, 15, 26);

        XSSFChart chart = drawing.createChart(anchor);
        chart.setTitleText("Статистика награды за блоки на пуле Nanopool");
        chart.setTitleOverlay(false);

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle("Дата");
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("ETH");

        XDDFDataSource<String> days = XDDFDataSourcesFactory.fromStringCellRange(sheet, new CellRangeAddress(1, sheet.getLastRowNum(), 0, 0));
        XDDFNumericalDataSource<Double> rewards = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, sheet.getLastRowNum(), 1, 1));

        XDDFChartData data = chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
        XDDFLineChartData.Series series = (XDDFLineChartData.Series) data.addSeries(days, rewards);
        series.setMarkerStyle(MarkerStyle.NONE);

        chart.plot(data);
    }
}
