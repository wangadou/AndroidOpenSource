package com.wwb.library.drawable;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;

import com.wwb.library.Library;
import com.wwb.library.config.DDLibraryConfig;

public class DDDrawableManager
{

	private int mainColor;
	private int strokeColor;
	private int mainColorPressed;
	private int grayColorPressed;

	private float corner;
	private int strokeWidth;

	public DDDrawableManager()
	{
		DDLibraryConfig config = Library.getInstance().getConfig();
		if (config != null)
		{
			setMainColor(config.getMainColor());
			setMainColorPressed(config.getMainColorPress());
			setStrokeColor(config.getStrokeColor());
			setGrayColorPressed(config.getGrayPressColor());
			setCorner(config.getCornerRadius());
			setStrokeWidth(config.getStrokeWidth());
		}
	}

	// -------------------------------mainColor
	public Drawable getLayerMainColor(boolean corner)
	{
		DDDrawable drawable = new DDDrawable();
		drawable.color(getMainColor()).strokeColor(getMainColor()).strokeWidthAll(getStrokeWidth());
		if (corner)
		{
			drawable.cornerAll(getCorner());
		}
		return drawable;
	}

	// --------------------------------white
	public Drawable getLayerWhiteStrokeItemSingle(boolean corner)
	{
		DDDrawable drawable = new DDDrawable();
		drawable.strokeColor(getStrokeColor()).strokeWidthAll(getStrokeWidth());
		if (corner)
		{
			drawable.cornerAll(getCorner());
		}
		return drawable;
	}

	public Drawable getLayerWhiteStrokeItemTop(boolean corner)
	{
		DDDrawable drawable = new DDDrawable();
		drawable.strokeColor(getStrokeColor()).strokeWidth(getStrokeWidth(), getStrokeWidth(), getStrokeWidth(), 0);
		if (corner)
		{
			drawable.corner(getCorner(), getCorner(), 0, 0);
		}
		return drawable;
	}

	public Drawable getLayerWhiteStrokeItemMiddle()
	{
		DDDrawable drawable = new DDDrawable();
		drawable.strokeColor(getStrokeColor()).strokeWidth(getStrokeWidth(), getStrokeWidth(), getStrokeWidth(), 0);
		return drawable;
	}

	public Drawable getLayerWhiteStrokeItemBottom(boolean corner)
	{
		DDDrawable drawable = new DDDrawable();
		drawable.strokeColor(getStrokeColor()).strokeWidthAll(getStrokeWidth());
		if (corner)
		{
			drawable.corner(0, 0, getCorner(), getCorner());
		}
		return drawable;
	}

	// --------------------------mainColorPress
	public Drawable getLayerMainColorPress(boolean corner)
	{
		DDDrawable drawable = new DDDrawable();
		drawable.color(getMainColorPressed()).strokeColor(getMainColorPressed()).strokeWidthAll(getStrokeWidth());
		if (corner)
		{
			drawable.cornerAll(getCorner());
		}
		return drawable;
	}

	// ---------------------------------gray
	public Drawable getLayerGrayStrokeItemSingle(boolean corner)
	{
		DDDrawable drawable = (DDDrawable) getLayerWhiteStrokeItemSingle(corner);
		drawable.color(getGrayColorPressed());
		return drawable;
	}

	public Drawable getLayerGrayStrokeItemTop(boolean corner)
	{
		DDDrawable drawable = (DDDrawable) getLayerWhiteStrokeItemTop(corner);
		drawable.color(getGrayColorPressed());
		return drawable;
	}

	public Drawable getLayerGrayStrokeItemMiddle()
	{
		DDDrawable drawable = (DDDrawable) getLayerWhiteStrokeItemMiddle();
		drawable.color(getGrayColorPressed());
		return drawable;
	}

	public Drawable getLayerGrayStrokeItemBottom(boolean corner)
	{
		DDDrawable drawable = (DDDrawable) getLayerWhiteStrokeItemBottom(corner);
		drawable.color(getGrayColorPressed());
		return drawable;
	}

	// ----------------------------selector
	public Drawable getSelectorWhiteGray(boolean corner)
	{
		DDDrawable white = new DDDrawable();
		if (corner)
		{
			white.cornerAll(getCorner());
		}

		DDDrawable gray = new DDDrawable();
		gray.color(getGrayColorPressed());
		if (corner)
		{
			gray.cornerAll(getCorner());
		}
		StateListDrawable drawable = DDDrawable.getStateListDrawable(white, null, null, gray);
		return drawable;
	}

	public Drawable getSelectorMainColor(boolean corner)
	{
		StateListDrawable drawable = DDDrawable.getStateListDrawable(getLayerMainColor(corner), null, null, getLayerMainColorPress(corner));
		return drawable;
	}

	public Drawable getSelectorWhiteGrayStrokeItemSingle(boolean corner)
	{
		StateListDrawable drawable = DDDrawable.getStateListDrawable(getLayerWhiteStrokeItemSingle(corner), null, null,
				getLayerGrayStrokeItemSingle(corner));
		return drawable;
	}

	public Drawable getSelectorWhiteGrayStrokeItemTop(boolean corner)
	{
		StateListDrawable drawable = DDDrawable.getStateListDrawable(getLayerWhiteStrokeItemTop(corner), null, null,
				getLayerGrayStrokeItemTop(corner));
		return drawable;
	}

	public Drawable getSelectorWhiteGrayStrokeItemMiddle()
	{
		StateListDrawable drawable = DDDrawable.getStateListDrawable(getLayerWhiteStrokeItemMiddle(), null, null, getLayerGrayStrokeItemMiddle());
		return drawable;
	}

	public Drawable getSelectorWhiteGrayStrokeItemBottom(boolean corner)
	{
		StateListDrawable drawable = DDDrawable.getStateListDrawable(getLayerWhiteStrokeItemBottom(corner), null, null,
				getLayerGrayStrokeItemBottom(corner));
		return drawable;
	}

	// ----------------------getter setter-----------------------

	public int getMainColor()
	{
		return mainColor;
	}

	public int getStrokeWidth()
	{
		return strokeWidth;
	}

	public void setStrokeWidth(int strokeWidth)
	{
		this.strokeWidth = strokeWidth;
	}

	public float getCorner()
	{
		return corner;
	}

	public void setCorner(float corner)
	{
		this.corner = corner;
	}

	public void setMainColor(int mainColor)
	{
		this.mainColor = mainColor;
	}

	public int getStrokeColor()
	{
		return strokeColor;
	}

	public void setStrokeColor(int strokeColor)
	{
		this.strokeColor = strokeColor;
	}

	public int getMainColorPressed()
	{
		return mainColorPressed;
	}

	public void setMainColorPressed(int mainColorPressed)
	{
		this.mainColorPressed = mainColorPressed;
	}

	public int getGrayColorPressed()
	{
		return grayColorPressed;
	}

	public void setGrayColorPressed(int grayColorPressed)
	{
		this.grayColorPressed = grayColorPressed;
	}

}
