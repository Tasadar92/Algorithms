/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author danie
 */
public class Board {
    int a[][];
	int size,wx,wy;
	int count;
	Board(int k,int x,int y)
	{
		size=k;
		a=new int[k][k];
		wx=x;wy=y;
		for(int i=0;i<size;i++)
			for(int j=0;j<size;j++)
				a[i][j]=0;

		a[wx][wy]=-1;
		count=0;

	}

	public void showBoard()
	{
		for(int i=0;i<size;i++)
		{	for(int j=0;j<size;j++)
				System.out.print(a[i][j]+"\t");
			System.out.println("");
		}
		System.out.println("");
	}

	int getPattern(int n,int i, int j)
	{
		n=n/2;
		i=i/n;
		j=j/n;
		if(i%2==0&&j%2==0)
		return 1;
		else if(i%2==0&&j%2!=0)
		return 2;
		else if(i%2!=0&&j%2==0)
		return 3;
		else return 4;
	}

	void fill_pattern(int p,int n,int i,int j,int wx1,int wy1)
	{
		count++;
		if(n==1)
		{	
			switch(p)
			{
				case 1:a[i][j+1]=a[i+1][j]=a[i+1][j+1]=count;break ;
				case 2:a[i][j]=a[i+1][j]=a[i+1][j+1]=count;break;
				case 3:a[i][j]=a[i][j+1]=a[i+1][j+1]=count;break;
				case 4:a[i][j+1]=a[i+1][j]=a[i][j]=count;break;
			}

			//showBoard();
			return;
		}
		else 
		{
			int p1=4,p2=3,p3=2,p4=1;
			i=i+n/2;j=j+n/2;
			int px1,px2,px3,px4,py1,py2,py3,py4;
			px1=i;py1=j;
			px2=i;py2=j+1;
			px3=i+1;py3=j;
			px4=i+1;py4=j+1;
			switch(p)
			{
				case 1:a[i][j+1]=a[i+1][j]=a[i+1][j+1]=count;p1=getPattern((n+1)/2,wx1,wy1);px1=wx1;py1=wy1;break;
				case 2:a[i][j]=a[i+1][j]=a[i+1][j+1]=count;p2=getPattern((n+1)/2,wx1,wy1);px2=wx1;py2=wy1;break;
				case 3:a[i][j]=a[i][j+1]=a[i+1][j+1]=count;p3=getPattern((n+1)/2,wx1,wy1);px3=wx1;py3=wy1;break;
				case 4:a[i][j+1]=a[i+1][j]=a[i][j]=count;p4=getPattern((n+1)/2,wx1,wy1);px4=wx1;py4=wy1;break;
			}
			//showBoard();
			fill_pattern(p1,n/2,i-(n/2),j-(n/2),px1,py1);
			fill_pattern(p2,n/2,i-(n/2),j+1,px2,py2);
			fill_pattern(p3,n/2,i+1,j-(n/2),px3,py3);
			fill_pattern(p4,n/2,i+1,j+1,px4,py4);
		}
	}
    
}
