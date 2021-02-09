import java.io.File;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LOC8R extends Application{
	private Database db = new Database(); 
	private ArrayList<Location> q = new ArrayList<>(); 
	private File file;
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		BorderPane rootPane = new BorderPane();
		Scene scene = new Scene(rootPane, 435, 500);
		stage.setTitle("LOC8R");
		VBox loading = new VBox();
		ImageView selectedImage = new ImageView();   
        Image image1 = new Image(this.getClass().getResourceAsStream("x.png"));
        selectedImage.setImage(image1);
        selectedImage.setScaleX(.8);
        selectedImage.setScaleY(.8);
        rootPane.setTop(selectedImage);
		ComboBox name = new ComboBox();
		//name.getItems().addAll("hotel","technology","government","auto repair","grocery store","accounting","biomedical","bakery","banking","restaurant","hospital","recreation","jewelry","auto","education","air travel","financial service","office supplies","transportation","venue","camping","public relations","retail","theater","physician","pest control","coffe shop","festival","livery service","air travel","dentist", "interior design","mesuem");
		name.getItems().addAll(db.list());
		name.getSelectionModel().selectFirst();
		name.setMaxWidth(100);
		TextField latitude= new TextField();
		latitude.setMaxWidth(250);
		TextField longitude= new TextField();
		longitude.setMaxWidth(250);
		loading.setSpacing(10);
		loading.setAlignment(Pos.CENTER);
		rootPane.setCenter(loading);
		Button search = new Button("Search");
		latitude.setPromptText("latitude");
		longitude.setPromptText("longitude");
		Text buttonNum = new Text();
		loading.getChildren().addAll(latitude, longitude, name, search);
		
		
		
		
		BorderPane r = new BorderPane();
		GridPane l = new GridPane();
		
		Button back = new Button("Back");
		back.setMinWidth(70);
		
		Button one = new Button();
		one.setMinSize(250, 59);
		Button two = new Button();
		two.setMinSize(250, 59);
		Button three = new Button();
		three.setMinSize(250, 59);
		Button four = new Button();
		four.setMinSize(250, 59);
		Button five = new Button();
		five.setMinSize(250, 59);
		Button six = new Button();
		six.setMinSize(250, 59);
		Button seven = new Button();
		seven.setMinSize(250, 59);
		Button eight = new Button();
		eight.setMinSize(250, 59);
		
		l.add(back, 0, 8);
		l.add(one, 1, 0);
		l.add(two, 1, 1);
		l.add(three, 1, 2);
		l.add(four, 1, 3);
		l.add(five, 1, 4);
		l.add(six, 1, 5);
		l.add(seven, 1, 6);
		l.add(eight, 1, 7);
		r.setCenter(l);
		
		
		
		Scene middle = new Scene(r, 435, 500);
		
			
		
		search.setOnAction(e -> {
			String type = (String) name.getValue();
			
			//ArrayList<Location> q = new ArrayList<>();
			q = db.getLocations(type, Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()));
			
			one.setText("");
			two.setText("");
			three.setText("");
			four.setText("");
			five.setText("");
			six.setText("");
			seven.setText("");
			eight.setText("");
			
			for(int i = 0; i< q.size();i++)
			{
				String text = q.get(i).getName()+"\n"+q.get(i).getAddress();
				if(i==0)
				{
					one.setText(text);
				}
				else if(i==1)
				{
					two.setText(text);
				}
				else if(i==2)
				{
					three.setText(text);
				}
				else if(i==3)
				{
					four.setText(text);
				}
				else if(i==4)
				{
					five.setText(text);
				}
				else if(i==5)
				{
					six.setText(text);
				}
				else if(i==6)
				{
					seven.setText(text);
				}
				else
				{
					eight.setText(text);
				}
				
			}
			for(int i = 0; i< q.size();i++)
				System.out.println(q.get(i).getName());
			stage.setScene(middle);
			stage.show();
		});
		
		
		
		back.setOnAction(e -> {
			name.getSelectionModel().selectFirst();
			latitude.setText("");
			longitude.setText("");
			stage.setScene(scene);
			stage.show();
		});
		
		BorderPane g = new BorderPane();
		TextArea com = new TextArea();
		g.setRight(com);
		Text blah = new Text("");
		VBox info = new VBox();
		g.setLeft(info);
		RadioButton like = new RadioButton("Like");
		RadioButton dislike = new RadioButton("Dislike");
		Button save = new Button("save");
		info.setSpacing(30);
		Button back2 = new Button("back");
		info.getChildren().addAll(blah,like,dislike,save,back2);
		
		
		back2.setOnAction(e -> {
			stage.setScene(middle);
			stage.show();
		});
		
		
		Scene end = new Scene(g, 435, 500);
		
		
		
		like.setOnAction(e -> {
			if(like.isSelected()== true && dislike.isSelected()==true)
			{
				dislike.setSelected(false);
			}
		});
		
		
		dislike.setOnAction(e -> {
			if(like.isSelected()== true && dislike.isSelected()==true)
			{
				like.setSelected(false);
			}
		});
		
		
		save.setOnAction(e -> {
			int bn = Integer.parseInt(buttonNum.getText());
			String type = (String) name.getValue();
			//ArrayList<Location> q = new ArrayList<>();
			//q = db.getLocations(type, Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()));
			q.get(bn).setComment(com.getText());
			if(like.isSelected()==true)
			q.get(bn).setRating(1);
			if(dislike.isSelected()==true)
			q.get(bn).setRating(-1);
			else
				q.get(bn).setRating(0);
			
			db.s();
		});
		
		
		
		
		one.setOnAction(e ->{
			if(one.getText().equals(""))
			{
			}
			else
			{
				buttonNum.setText("0");;
				String type = (String) name.getValue();
				//ArrayList<Location> q = new ArrayList<>();
				//q = db.getLocations(type, Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()));
				Location t =q.get(0);
				
				System.out.println(q);
				blah.setText(t.getType()+"\n"+t.getName()+"\n"+t.getAddress()+"\n"+t.getLatitude()+", "+ t.getLongitude());
				com.setText(t.getComment());
				if(t.getRating()==1)
				{
					like.setSelected(true);
					dislike.setSelected(true);
				}
				else if(t.getRating() == -1)
				{
					dislike.setSelected(true);
					like.setSelected(false);
				}
				else
				{
					like.setSelected(false);
					dislike.setSelected(false);
				}
				stage.setScene(end);
				stage.show();
			}
			
			
		});
		
		
		
		
		
		
		two.setOnAction(e ->{
			if(two.getText().equals(""))
			{
			}
			else
			{
				buttonNum.setText("1");;
				String type = (String) name.getValue();
				//ArrayList<Location> q = new ArrayList<>();
				//q = db.getLocations(type, Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()));
				Location t =q.get(1);
				blah.setText(t.getType()+"\n"+t.getName()+"\n"+t.getAddress()+"\n"+t.getLatitude()+", "+ t.getLongitude());
				com.setText(t.getComment());
				if(t.getRating()==1)
				{
					like.setSelected(true);
					dislike.setSelected(true);
				}
				else if(t.getRating() == -1)
				{
					dislike.setSelected(true);
					like.setSelected(false);
				}
				else
				{
					like.setSelected(false);
					dislike.setSelected(false);
				}
				stage.setScene(end);
				stage.show();
			}
			
			
		});
		
		
		three.setOnAction(e ->{
			if(three.getText().equals(""))
			{
			}
			else
			{
				buttonNum.setText("2");;
				String type = (String) name.getValue();
				//ArrayList<Location> q = new ArrayList<>();
				//q = db.getLocations(type, Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()));
				Location t =q.get(2);
				blah.setText(t.getType()+"\n"+t.getName()+"\n"+t.getAddress()+"\n"+t.getLatitude()+", "+ t.getLongitude());
				com.setText(t.getComment());
				if(t.getRating()==1)
				{
					like.setSelected(true);
					dislike.setSelected(true);
				}
				else if(t.getRating() == -1)
				{
					dislike.setSelected(true);
					like.setSelected(false);
				}
				else
				{
					like.setSelected(false);
					dislike.setSelected(false);
				}
				stage.setScene(end);
				stage.show();
			}
			
			
		});
		
		
		
		four.setOnAction(e ->{
			if(four.getText().equals(""))
			{
			}
			else
			{
				buttonNum.setText("3");;
				String type = (String) name.getValue();
			//	ArrayList<Location> q = new ArrayList<>();
				//q = db.getLocations(type, Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()));
				Location t =q.get(3);
				blah.setText(t.getType()+"\n"+t.getName()+"\n"+t.getAddress()+"\n"+t.getLatitude()+", "+ t.getLongitude());
				com.setText(t.getComment());
				if(t.getRating()==1)
				{
					like.setSelected(true);
					dislike.setSelected(true);
				}
				else if(t.getRating() == -1)
				{
					dislike.setSelected(true);
					like.setSelected(false);
				}
				else
				{
					like.setSelected(false);
					dislike.setSelected(false);
				}
				stage.setScene(end);
				stage.show();
			}
			
			
		});
		
		
		five.setOnAction(e ->{
			if(five.getText().equals(""))
			{
			}
			else
			{
				buttonNum.setText("4");;
				String type = (String) name.getValue();
			//	ArrayList<Location> q = new ArrayList<>();
				//q = db.getLocations(type, Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()));
				Location t =q.get(4);
				blah.setText(t.getType()+"\n"+t.getName()+"\n"+t.getAddress()+"\n"+t.getLatitude()+", "+ t.getLongitude());
				com.setText(t.getComment());
				if(t.getRating()==1)
				{
					like.setSelected(true);
					dislike.setSelected(true);
				}
				else if(t.getRating() == -1)
				{
					dislike.setSelected(true);
					like.setSelected(false);
				}
				else
				{
					like.setSelected(false);
					dislike.setSelected(false);
				}
				stage.setScene(end);
				stage.show();
			}
			
			
		});
		
		
		
		six.setOnAction(e ->{
			if(six.getText().equals(""))
			{
			}
			else
			{
				buttonNum.setText("5");;
				String type = (String) name.getValue();
			//	ArrayList<Location> q = new ArrayList<>();
				//q = db.getLocations(type, Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()));
				Location t =q.get(5);
				blah.setText(t.getType()+"\n"+t.getName()+"\n"+t.getAddress()+"\n"+t.getLatitude()+", "+ t.getLongitude());
				com.setText(t.getComment());
				if(t.getRating()==1)
				{
					like.setSelected(true);
					dislike.setSelected(true);
				}
				else if(t.getRating() == -1)
				{
					dislike.setSelected(true);
					like.setSelected(false);
				}
				else
				{
					like.setSelected(false);
					dislike.setSelected(false);
				}
				stage.setScene(end);
				stage.show();
			}
			
			
		});
		
		
		
		seven.setOnAction(e ->{
			if(seven.getText().equals(""))
			{
			}
			else
			{
				buttonNum.setText("6");;
				String type = (String) name.getValue();
			//	ArrayList<Location> q = new ArrayList<>();
				//q = db.getLocations(type, Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()));
				Location t =q.get(6);
				blah.setText(t.getType()+"\n"+t.getName()+"\n"+t.getAddress()+"\n"+t.getLatitude()+", "+ t.getLongitude());
				com.setText(t.getComment());
				if(t.getRating()==1)
				{
					like.setSelected(true);
					dislike.setSelected(true);
				}
				else if(t.getRating() == -1)
				{
					dislike.setSelected(true);
					like.setSelected(false);
				}
				else
				{
					like.setSelected(false);
					dislike.setSelected(false);
				}
				stage.setScene(end);
				stage.show();
			}
			
			
		});
		
		
		
		eight.setOnAction(e ->{
			if(eight.getText().equals(""))
			{
			}
			else
			{
				buttonNum.setText("7");;
				String type = (String) name.getValue();
			//	ArrayList<Location> q = new ArrayList<>();
			//	q = db.getLocations(type, Double.parseDouble(latitude.getText()), Double.parseDouble(longitude.getText()));
				Location t =q.get(7);
				blah.setText(t.getType()+"\n"+t.getName()+"\n"+t.getAddress()+"\n"+t.getLatitude()+", "+ t.getLongitude());
				com.setText(t.getComment());
				if(t.getRating()==1)
				{
					like.setSelected(true);
					dislike.setSelected(true);
				}
				else if(t.getRating() == -1)
				{
					dislike.setSelected(true);
					like.setSelected(false);
				}
				else
				{
					like.setSelected(false);
					dislike.setSelected(false);
				}
				stage.setScene(end);
				stage.show();
			}
			
			
		});
		
		
		
		stage.setScene(scene);
		stage.show();
		
	}
	
	
	

}
