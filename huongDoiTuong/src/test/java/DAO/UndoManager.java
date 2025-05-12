package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.table.DefaultTableModel;

public class UndoManager {
    private Stack<List<Object[]>> undoStack = new Stack<>();

    // Lưu trạng thái hiện tại
    public void saveState(DefaultTableModel model) {
        List<Object[]> currentState = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            Object[] row = new Object[model.getColumnCount()];
            for (int j = 0; j < model.getColumnCount(); j++) {
                row[j] = model.getValueAt(i, j);
            }
            currentState.add(row);
        }
        undoStack.push(currentState);
    }

    // Hoàn tác trạng thái
    public boolean undo(DefaultTableModel model) {
        if (undoStack.isEmpty()) {
            return false;
        }
        List<Object[]> previousState = undoStack.pop();
        model.setRowCount(0);
        for (Object[] row : previousState) {
            model.addRow(row);
        }
        return true;
    }
}
