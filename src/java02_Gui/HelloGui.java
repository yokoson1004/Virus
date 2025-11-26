package java02_Gui;

import java.awt.Container;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class HelloGui extends JFrame {
	
	public HelloGui(String title) {
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(800, 600);
		
        // ラベルの作成と追加
        MyPanel panel = new MyPanel();
        Container c = this.getContentPane();
        c.add(panel);
        
        // ここからWindowListenerの追加（通常のクラスを利用）
        MyWindowListener myListener = new MyWindowListener(panel);
        this.addWindowListener(myListener);
	
	}


	public static void main(String[] args) {
		JFrame frame = new HelloGui("感染症シミュレーション");
		frame.setVisible(true);

	}
	
	public class MyWindowListener implements WindowListener {
		private MyPanel panel;
		
		public MyWindowListener(MyPanel panel) {
			super();
			this.panel = panel;
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			System.out.println("終了処理");
			panel.toCSV();
			
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
		
	}

	 

}
