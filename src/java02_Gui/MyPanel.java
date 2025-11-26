/*
 * 22F1048　横山颯太　
 * 変更点　白の時の移動速度を２倍にし、黄色の時の速度を遅くした。また、黄色の時間を初期の２倍にした。
 */

package java02_Gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class MyPanel extends JPanel implements ActionListener {
	Timer t;

	ArrayList<Ball>list;
	ArrayList<Integer> log;
	Random rand;
	Rectangle hole;
	int n = 10;
	
	public MyPanel() {
		this.setBackground(Color.black);
		t = new Timer(100, this);
		t.setCoalesce(true);
		t.setRepeats(true);
		t.start();

		hole=new Rectangle(400,300,60,60); 

		rand=new Random();
		
		list=new ArrayList<Ball>();
		log = new ArrayList<Integer>();
		Ball ball = new Ball(hole);
		ball.setPandemic(true);
		list.add(ball);
		for(int i = 1; i<n; i++) {
			ball = new Ball(hole);
			ball.setPandemic(false);
			list.add(ball);

		}

		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int n_red = 0;
		for (Ball ball : list) {
			ball.paint(g);
			if(ball.isPandemic() == true) {
				n_red=n_red+1;
			}
		}
		log.add(n_red);
		
		Font f = new Font("ＭＳ ゴシック",Font.PLAIN,20);
		g.setFont(f);
		g.setColor(Color.white);
		g.drawString("横山　颯太 "+list.size() + " 赤："+n_red, 0, this.getHeight());
		g.drawRect(hole.x,hole.y,hole.width,hole.height);
		

			
		}
	

	
	@Override
	public void actionPerformed(ActionEvent e) {
	    //ArrayList<Ball> removeList = new ArrayList<Ball>();
	    //ArrayList<Ball> addList = new ArrayList<Ball>();

	    for(Ball ball : list) {
	        ball.move(this.getWidth(), this.getHeight());
	       // if(ball.isFall()) {
	        //    removeList.add(ball);
	       //     System.out.println("kieta!");
	     //   }
	        // 下のコメントアウトされた部分は、変更要求の
	        // "ボールが上に当たった際に出現するボールの数を1個から2個にした。" に関連しているようです。
	        // しかし、提供されたコードには `Ball` クラスに `isUpper()` メソッドが定義されていません。
	        // もし `isUpper()` が `Ball` クラスのメソッドとして意図されている場合、
	        // それを実装する必要があります。今のところ、コメントアウトされたままにしておきます。
	        // if(ball.isUpper()) {
	        //     addList.add(new Ball(hole));
	        //     addList.add(new Ball(hole));
	        // }
	    }
	    
	    for(int j = 0; j < list.size(); j++){
	        Ball ball0 = list.get(j);
	        for(int i = j + 1; i < list.size(); i++) {
	            Ball ball1 = list.get(i);
	            ball0.isTouch(ball1);
	            
	           
	        }
	    }

	    //list.removeAll(removeList);
	    //list.addAll(addList);
	    
	    if(list.size() <= 1) { //1以下ならボール増やす
	        list.add(new Ball(hole));
	    }

	    repaint();
	}
	public void toCSV() {
        try {
            // 出力ファイルの作成
            FileWriter fw = new FileWriter("感染症.csv", false);
            // PrintWriterクラスのオブジェクトを生成
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
            
            for(Integer red_n : log) {
            	pw.println(red_n);
            }
            
            // ファイルを閉じる
            pw.close();
 
            // 出力確認用のメッセージ
            System.out.println("csvファイルを出力しました");
 
        // 出力に失敗したときの処理
        } catch (IOException ex) {
            ex.printStackTrace();
        
        }
	}

}