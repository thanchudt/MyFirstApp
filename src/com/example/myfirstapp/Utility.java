package com.example.myfirstapp;

public class Utility {
	public int mod(int x, int y)
	{
	    int result = x % y;
	    return result < 0? result + y : result;
	}
}
