/**
 * 
 */
package com.resttemplate.sample.web;

	class FactoryOuter {

		  FactoryInner[] fi = new FactoryInner[3];
		  private int lastIndex = 0;
		  private int x = 0;

		  public FactoryOuter(int x)
		  {
		    this.x = x;
		  }

		  public int getX()
		  {
		    return x;
		  }

		  public void addInner(int y)
		  {
		    if (lastIndex < fi.length)
		    {
		      fi[lastIndex++] = new FactoryInner(y);
		    }
		    else throw new RuntimeException("FactoryInner array full");
		  }

		  public void list()
		  {
		    for (int i = 0; i < fi.length; i++)
		    {
		      System.out.print("I can see into the inner class where y = " +
		                       fi[i].y + " or call display: ");
		      fi[i].display();
		    }
		  }

		  public class FactoryInner
		  {
		    private int y;
		    private FactoryInner(int y)
		    {
		      this.y = y;
		    }
		    public void display()
		    {
		      System.out.println("FactoryInner x =  " + x + " and y = " + y);
		    }
		  }
		}

//		public class FactoryInnerOuter{
//
//		    public static void main(String[] args)
//		    {
//		        FactoryOuter fo = new FactoryOuter(1);
//		        fo.addInner(101);
//		        fo.addInner(102);
//		        fo.addInner(103);
//		        fo.list();
//		        //fo.addInner(104);
//		    }
//		}
