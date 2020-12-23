
package main.java.org.javafx.studentsmanagementsystem.tools;

import org.controlsfx.control.Notifications;
import org.kordamp.ikonli.javafx.FontIcon;

import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Labeled;
import javafx.scene.paint.Color;
import javafx.util.Duration;


public final class JavaFXTools {
	
	private JavaFXTools() {
	}
	

	public static void showNotification(String title , String text , Duration duration , NotificationType notificationType) {
		Platform.runLater(() -> showNotification(title, text, duration, notificationType, null));
	}
	

	public static void showNotification(String title , String text , Duration duration , NotificationType notificationType , Node graphic) {
		
		try {

			if (!Platform.isFxApplicationThread()) {
				Platform.runLater(() -> showNotification(title, text, duration, notificationType, graphic));
				return;
			}

			showNotification2(title, text, duration, notificationType, graphic);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	private static void showNotification2(String title , String text , Duration duration , NotificationType notificationType , Node graphic) {
		Notifications notification1;

		if (graphic == null)
			notification1 = Notifications.create().title(title).text(text).hideAfter(duration).darkStyle().position(Pos.BOTTOM_RIGHT);
		else
			notification1 = Notifications.create().title(title).text(text).hideAfter(duration).darkStyle().position(Pos.BOTTOM_RIGHT).graphic(graphic);

		switch (notificationType) {
			case CONFIRM:
				notification1.graphic(JavaFXTools.getFontIcon("fas-question-circle", Color.web("#ad14e2"), 32)).show();
				break;
			case ERROR:
				notification1.graphic(JavaFXTools.getFontIcon("fas-times", Color.web("#f83e3e"), 32)).show();
				break;
			case INFORMATION:
				notification1.graphic(JavaFXTools.getFontIcon("fas-info-circle", Color.web("#1496e5"), 32)).show();
				break;
			case SIMPLE:
				notification1.show();
				break;
			case WARNING:
				notification1.graphic(JavaFXTools.getFontIcon("fa-warning", Color.web("#d74418"), 32)).show();
				break;
			case SUCCESS:
				notification1.graphic(JavaFXTools.getFontIcon("fas-check", Color.web("#64ff41"), 32)).show();
				break;
			default:
				break;
		}
	}

	public static void setFontIcon(Labeled node , FontIcon icon , String iconLiteral , Color color) {
		icon.setIconLiteral(iconLiteral);
		icon.setIconColor(color);
		if (node != null)
			node.setGraphic(icon);
	}

	public static FontIcon getFontIcon(String iconLiteral , Color color , int size) {

		FontIcon icon = new FontIcon(iconLiteral);

		icon.setIconColor(color);

		if (size != 0)
			icon.setIconSize(size);
		
		return icon;
	}
	
}
