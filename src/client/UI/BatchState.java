package client.UI;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import shared.model.Field;
import shared.model.Record;
import client.indexer.Cell;
import client.indexer.TableModel;
import client.listeners.BatchStateListener;

public class BatchState {
		
		List<Field> fields;
		List<Record> records;
		
		int recordCount = 6;
		int fieldCount = 6;
		int batchID = 2;
		int firstycoord;
		int recordHeight;
		double imageScale = 1.0;
		int imagexOrigin = 0;
		int imageyOrigin = 0;
		int windowXOrigin = 100;
		int windowYOrigin = 100;
		int windowWidth = 900;
		int windowHeight = 700;
		int mainSplitLocation = 400;
		int bottomSplitLocation = 600;
		boolean imageInverted = false;
		private String[][] values;
		private Cell selectedCell;
		transient private List<BatchStateListener> listeners;
		String imageLocation;
		transient private static BufferedImage NULL_IMAGE = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
		transient private BufferedImage image;
		transient private TableModel tableModel = null;
		
		public BatchState(int recordCount, int fieldCount) {
			this.image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);

			this.recordCount = recordCount;
			this.fieldCount = fieldCount;
			values = new String[recordCount][fieldCount];
			selectedCell = new Cell(1, 1);
			listeners = new ArrayList<BatchStateListener>();
			setRecordNumbers();
		}
		
		public void setRecordNumbers()
		{
			for(int i = 0; i < recordCount; i++)
			{
				setValue(new Cell(i, 0), Integer.toString(i+1));
			}
		}
		
		public void addListener(BatchStateListener l) {
			listeners.add(l);
		}
		
		public void setValue(Cell cell, String value) {
			
			values[cell.getRow()][cell.getCol()] = value;
			
			for (BatchStateListener l : listeners) {
				l.valueChanged(cell, value);
			}
		}
		
		public void setValueAt(Cell cell, String value) {
			
			values[cell.getRow()][cell.getCol()] = value;

		}
		
		public String getValue(Cell cell) {
			return values[cell.getRow()][cell.getCol()];
		}
		
		public void setSelectedCell(Cell selCell) {
			
			selectedCell = selCell;
			
			for (BatchStateListener l : listeners) {
				l.selectedCellChanged(selCell);
			}
		}
		
		public Cell getSelectedCell() {
			return selectedCell;
		}
		
		public void loadImage(String imageFile) {
			try {
				this.imageLocation = imageFile;
				image = ImageIO.read(new URL(imageLocation));
			}
			catch (IOException e) {
				image = NULL_IMAGE;
			}
		}

		public int getRecordCount() {
			return recordCount;
		}

		public void setRecordCount(int recordCount) {
			this.recordCount = recordCount;
		}

		public int getFieldCount() {
			return fieldCount;
		}

		public void setFieldCount(int fieldCount) {
			this.fieldCount = fieldCount;
		}

		public List<Field> getFields() {
			return fields;
		}

		public void setFields(List<Field> fields) {
			this.fields = fields;
			fields.add(0, new Field("Record Number", "", "", -1, -1, -1, -1));
		}

		public List<Record> getRecords() {
			return records;
		}

		public void setRecords(List<Record> records) {
			this.records = records;
		}
		
		public String getFieldName(int fieldIndex)
		{
			return fields.get(fieldIndex).getTitle();
		}
		
		public String[][] getValues() {
			return values;
		}

		public void setValues(String[][] values) {
			this.values = values;
		}
		
		public String valuesToString()
		{
			StringBuilder sb = new StringBuilder();

			for(int i=0; i < recordCount; i++)
			{
				for(int j=1; j < fieldCount; j++)
				{
					sb.append(values[i][j]);
					if(j != fieldCount-1)
					{
						sb.append(",");
						
					}
				}
				if(i != recordCount-1)
				{
					sb.append(";");
				}
			}
			
			System.out.println("Here is what i am returning: " + sb.toString());
			return sb.toString();
		}

		public List<BatchStateListener> getListeners() {
			return listeners;
		}

		public void setListeners(List<BatchStateListener> listeners) {
			this.listeners = listeners;
		}

		public static BufferedImage getNULL_IMAGE() {
			return NULL_IMAGE;
		}

		public static void setNULL_IMAGE(BufferedImage nULL_IMAGE) {
			NULL_IMAGE = nULL_IMAGE;
		}

		public BufferedImage getImage() {
			return image;
		}

		public void setImage(BufferedImage image) {
			this.image = image;
		}

		public int getBatchID() {
			return batchID;
		}

		public void setBatchID(int batchID) {
			this.batchID = batchID;
		}

		public int getFirstycoord() {
			return firstycoord;
		}

		public void setFirstycoord(int firstycoord) {
			this.firstycoord = firstycoord;
		}

		public int getRecordHeight() {
			return recordHeight;
		}

		public void setRecordHeight(int recordHeight) {
			this.recordHeight = recordHeight;
		}

		public TableModel getTableModel() {
			return tableModel;
		}

		public void setTableModel(TableModel tableModel) {
			this.tableModel = tableModel;
		}

		public double getScale() {
			return imageScale;
		}

		public void setScale(double imageScale) {
			this.imageScale = imageScale;
		}

		public int getXOrigin() {
			return imagexOrigin;
		}

		public void setXOrigin(int imagexOrigin) {
			this.imagexOrigin = imagexOrigin;
		}

		public int getYOrigin() {
			return imageyOrigin;
		}

		public void setYOrigin(int imageyOrigin) {
			this.imageyOrigin = imageyOrigin;
		}

		public boolean isImageInverted() {
			return imageInverted;
		}

		public void setImageInverted(boolean imageInverted) {
			this.imageInverted = imageInverted;
		}

		public String getImageLocation() {
			return imageLocation;
		}

		public void setImageLocation(String imageLocation) {
			this.imageLocation = imageLocation;
		}

		public double getImageScale() {
			return imageScale;
		}

		public void setImageScale(double imageScale) {
			this.imageScale = imageScale;
		}

		public int getImagexOrigin() {
			return imagexOrigin;
		}

		public void setImagexOrigin(int imagexOrigin) {
			this.imagexOrigin = imagexOrigin;
		}

		public int getImageyOrigin() {
			return imageyOrigin;
		}

		public void setImageyOrigin(int imageyOrigin) {
			this.imageyOrigin = imageyOrigin;
		}

		public int getWindowXOrigin() {
			return windowXOrigin;
		}

		public void setWindowXOrigin(int windowXOrigin) {
			this.windowXOrigin = windowXOrigin;
		}

		public int getWindowYOrigin() {
			return windowYOrigin;
		}

		public void setWindowYOrigin(int windowYOrigin) {
			this.windowYOrigin = windowYOrigin;
		}

		public int getWindowWidth() {
			return windowWidth;
		}

		public void setWindowWidth(int windowWidth) {
			this.windowWidth = windowWidth;
		}

		public int getWindowHeight() {
			return windowHeight;
		}

		public void setWindowHeight(int windowHeight) {
			this.windowHeight = windowHeight;
		}

		public int getMainSplitLocation() {
			return mainSplitLocation;
		}

		public void setMainSplitLocation(int mainSplitLocation) {
			this.mainSplitLocation = mainSplitLocation;
		}

		public int getBottomSplitLocation() {
			return bottomSplitLocation;
		}

		public void setBottomSplitLocation(int bottomSplitLocation) {
			this.bottomSplitLocation = bottomSplitLocation;
		}

		public void setDummyFields(){
			fields = new ArrayList<Field>();
			Field f = new Field();
			f.setTitle("Hi");
			fields.add(f);
			Field fi = new Field();
			fi.setTitle("My");
			fields.add(fi);
			Field fie = new Field();
			fie.setTitle("Name");
			fields.add(fie);
			Field fiel = new Field();
			fiel.setTitle("Is");
			fields.add(fiel);
			Field field = new Field();
			field.setTitle("Moe");
			fields.add(field);
		}

	}
