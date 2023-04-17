package listeners;

import java.awt.Component;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class MouseWheelSlide implements MouseWheelListener {
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		Component component = e.getComponent();
		if(e.getWheelRotation() != 0) {
			if(component.getLocation().y - e.getWheelRotation()*20 <= 0)
				if(component.getLocation().y - e.getWheelRotation()*20 >= -component.getLocation().y)
					component.setLocation(component.getLocation().x, component.getLocation().y - e.getWheelRotation()*20);
		}
	}
}
