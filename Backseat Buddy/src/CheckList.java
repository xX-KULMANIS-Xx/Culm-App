import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

class CheckListRenderer extends JCheckBox implements ListCellRenderer<Entity> {

    @Override
    public Component getListCellRendererComponent(JList<? extends Entity> list, 
        Entity value, int index, boolean isSelected, boolean cellHasFocus) {

            setEnabled(list.isEnabled());
            setSelected(value.isActive()); // sets the checkbox
            setFont(list.getFont());
        if (isSelected) { // highlights the currently selected entry
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
        setText(value.toString()+" - A" + value.isActive()+" - F"+cellHasFocus+" - S"+isSelected );
        return this;
    }

}