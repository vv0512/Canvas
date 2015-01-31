package com.parsers;

public class CanvasDataType {
	public int id = -1;
	public String name = "";
	public CanvasState state = CanvasState.unknown;
	public boolean verified = false;
	
	public CanvasDataType(){
		
	}
	
	protected int verifyId(String s)
	{
		int id = -1;
		try {
			id = Integer.valueOf(s).intValue();
		}
		catch (NumberFormatException e){
			System.out.println(e.getMessage());
		}
		return id;
	}
	
	protected CanvasState verifyState(String s)
	{
		try {
			if (Integer.valueOf(s) == 1)
				return (CanvasState.active);
			if (Integer.valueOf(s) == 0)
				return (CanvasState.deleted);
		}
		catch (NumberFormatException e){
			if (s.equalsIgnoreCase("active"))
				return CanvasState.active;
			else if (s.equalsIgnoreCase("deleted"))
				return CanvasState.deleted;
		}
		
		return CanvasState.unknown;
	}
	
}