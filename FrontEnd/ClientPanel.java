package Morpion.FrontEnd;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;

public class ClientPanel extends Parent {
	private TextArea textToSend;
	private ScrollPane scrollReceivedText;
	private TextFlow receivedText;
	private Button sendBtn;
	private Button clearBtn;
	//private Label messageAEnvoye  = new Label();
	public ClientPanel() {
		
		this.textToSend = new TextArea();
		this.scrollReceivedText = new ScrollPane();
		this.receivedText = new TextFlow();
		this.sendBtn = new Button();
		this.clearBtn = new Button();
		//this.messageAEnvoye.setText(""); 
		
		this.getChildren().add(this.scrollReceivedText);
		this.getChildren().add(this.textToSend);
		this.getChildren().add(this.clearBtn);
		this.getChildren().add(this.sendBtn);
		
		
		scrollReceivedText.setLayoutX(50);
		scrollReceivedText.setLayoutY(50);
		scrollReceivedText.setPrefWidth(400);
		scrollReceivedText.setPrefHeight(350);
		scrollReceivedText.setContent(receivedText);
		scrollReceivedText.vvalueProperty().bind(receivedText.heightProperty());
		
		
		textToSend.setEditable(true);
		textToSend.setLayoutX(50);
		textToSend.setLayoutY(420);
		textToSend.setPrefWidth(400);
		textToSend.setPrefHeight(100);
		
		
		receivedText.setVisible(true);
		
		
		sendBtn.setVisible(true);
		sendBtn.setLayoutX(50);
		sendBtn.setLayoutY(540);
		sendBtn.setPrefWidth(190);
		sendBtn.setPrefHeight(50);
		sendBtn.setText("Envoyer");
	
		clearBtn.setVisible(true);
		clearBtn.setLayoutX(270);
		clearBtn.setLayoutY(540);
		clearBtn.setPrefWidth(190);
		clearBtn.setPrefHeight(50);
		clearBtn.setText("Effacer");
		
		
		
		sendBtn.setOnAction(new EventHandler<ActionEvent>() 
		{
			@Override
			public void handle(ActionEvent event) 
			{
				Label messageAEnvoye = new Label();
				//String messageAEnvoye = "";
				//messageAEnvoye.setText(textToSend.getText() + "\n");			
				//receivedText.getChildren().add(messageAEnvoye);
				
				messageAEnvoye.setText(textToSend.getText());
				//messageAEnvoye = textToSend.getText();
				
				
				//messageAEnvoye.setLayoutX()
				
				
				receivedText.getChildren().add(messageAEnvoye);
				
				textToSend.setText("");
				
			}
		});

	}

}





