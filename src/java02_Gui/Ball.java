/*22F1048 横山颯太*/
package java02_Gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	private int x;
	private int y;
	private int dx;
	private int dy;
	private int r;
	private Color c;
	private Rectangle hole;
	private int hit_counter;
	//private Color save_c;
	
	public Ball(Rectangle hole) {
		Random rand=new Random();
		x=rand.nextInt(0,800);
		y=rand.nextInt(0,600);

		dx=rand.nextInt(30,50); 
		dy=rand.nextInt(30,50);
		r=rand.nextInt(10,30);
		
		c=Color.white;
		hit_counter=0;
		this.hole=hole;
	}

	public Ball(Rectangle hole,Ball ball0,Ball ball1) {
		Random rand=new Random();
		int d;
		if(ball0.r>ball1.r) {
			d=ball0.r*2;
		}
		else {
			d=ball1.r*2;
		}

		x=(ball0.x+ball1.x)/2+d;
		y=(ball0.y+ball1.y)/2+d;
		
	
		dx=ball0.dy; 
		dy=ball0.dx;
		r=rand.nextInt(10,30);
		
		int red=rand.nextInt(256);
		int green=rand.nextInt(256);
		int blue=rand.nextInt(256);
		//save_c=new Color(red,green,blue);
		c=Color.white;
		hit_counter=100;
		this.hole=hole;
		
	}

	public void paint(Graphics g) {
		g.setColor(c);
		g.fillOval(x,y,2*r,2*r);
		g.setColor(Color.pink);
		g.drawRect(x+r/2, y+r/2, r, r);
		
	}
	
	public void move(int wallWidth,int wallHeight) {
		if(c != Color.red) { //赤じゃない時移動する
			if(c == Color.yellow) { //黄色の時は移動速度遅（ダウンタイム）
				x = x + dx * 1/2;
				y = y + dy * 1/2;
			}
			else { //白の時は移動速度速
				x=x+dx * 2;
				y=y+dy * 2;
			}
			

			if (x+r*2 > wallWidth) {
				x= wallWidth - r*2;
				dx = dx* -1;       
			}

			if (y+r*2 > wallHeight) {
				y= wallHeight - r*2;
				dy= dy * -1;
			}

			if (x< 0) {
				x = 0;
				dx = dx * -1;
			}
		
			if (y < 0) {
				y = 0;
				dy = dy * -1;
			}
			
			
		}
	
		
		if (hit_counter == 1) {
			hit_counter = -20; //復帰したあと、感染しない時間。黄色でいる時間を10から20に変更
			c = Color.yellow;
		}
		else if(hit_counter < 0) {
			hit_counter = hit_counter + 1;
		}
		else if(hit_counter > 0) {
			hit_counter = hit_counter - 1;
		}
		else {
			c = Color.white; 
		}
	}
	
	public boolean isFall() {
		if(hole.contains(x,y,r*2,r*2)){
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isUpper() {
	
		if(y <= 0) {
			return true;
		}
		else {
			return false;
		}
	}
	public int isCollusion(Ball another) {
		
		int x0=this.x+this.r;
		int y0=this.y+this.r;
		int x1=another.x+another.r;
		int y1=another.y+another.r;
		
	
		int d_squared=(x1-x0)*(x1-x0)+(y1-y0)*(y1-y0);
		
	
		if (d_squared <= (this.r+another.r)*(this.r+another.r)) {
			// another.c=this.c;
			
		
			int dxt=this.dx;
			int dyt=this.dy;
			this.dx=another.dx;
			this.dy=another.dy;
			another.dx=dxt;
			another.dy=dyt;
			System.out .println("atatta"); 
			
			
			if(this.hit_counter==0 && another.hit_counter==0) {
				this.hit_counter=50; 
				another.hit_counter=-50; 
				this.c=Color.yellow;
				another.c=Color.yellow;
				return 1; 
			}
			else if(this.hit_counter==0 && another.c==Color.white){
				return -1; 
			}
			else if(this.c==Color.white && another.hit_counter==0) {
				return -2; 
			}
			else if(this.c==Color.yellow && another.hit_counter==0) {
				return -1;
			}
			else if(this.hit_counter==0 && another.c==Color.yellow) {
				return -2; 
			}
			else{
				return 0; 
			}
		}
		else {
			return 0; 
		}
	}
public void isTouch(Ball another) {
		
		int x0=this.x+this.r;
		int y0=this.y+this.r;
		int x1=another.x+another.r;
		int y1=another.y+another.r;
		
	
		int d_squared=(x1-x0)*(x1-x0)+(y1-y0)*(y1-y0);
		
	
		if (d_squared <= (this.r+another.r)*(this.r+another.r)) {
			// another.c=this.c;
			
		
			int dxt=this.dx;
			int dyt=this.dy;
			this.dx=another.dx;
			this.dy=another.dy;
			another.dx=dxt;
			another.dy=dyt;
			System.out .println("atatta"); 
			Random rand = new Random();
			if(this.hit_counter > 0 && another.hit_counter == 0) {
				double r = rand.nextDouble();
				if(r < 0.5) {
					another.c = Color.red;
					another.hit_counter = 100;
				}
			
			}
			else if(this.hit_counter == 0 && another.hit_counter > 0) {
				double r = rand.nextDouble();
				if (r > 0.5) {
					this.c = Color.red;
					this.hit_counter = 100;
				}
		
			}
		}
	}
	
	public void setPandemic(boolean sick) {
		//sick true:感染している。 false:健康
		if(sick == true) {
			c = Color.red;
			hit_counter = 100;
		}
		else { 
			c = Color.white;
			hit_counter = 0;
		}
	}
	
	public boolean isPandemic() {
		if(c == Color.red) {
			return true;
		}
		else {
			return false;
		}
	}
}