package model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shetroows")
@XmlAccessorType(XmlAccessType.FIELD)
public class SheetRows {

	@XmlElement(name = "sheetrow")
	public List<SheetRow> sheetrows;

	public SheetRows() {
		this.sheetrows = new ArrayList<SheetRow>();
	}

	public List<SheetRow> getSheetrows() {
		return sheetrows;
	}

	public void setSheetrows(List<SheetRow> sheetrows) {
		this.sheetrows = sheetrows;
	}

}
