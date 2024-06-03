import javax.swing.JCheckBox;
import javax.swing.JList;

public void addCheckbox(JCheckBox checkBox) {
    ListModel currentList = this.getModel();
    JCheckBox[] newList = new JCheckBox[currentList.getSize() + 1];
    for (int i = 0; i < currentList.getSize(); i++) {
        newList[i] = (JCheckBox) currentList.getElementAt(i);
    }
    newList[newList.length - 1] = checkBox;
    setListData(newList);
}