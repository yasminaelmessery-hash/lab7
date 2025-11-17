package Lab_7;

import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import java.util.Map;
import java.util.HashMap;


public class StudentDashboard extends javax.swing.JFrame {

    private Student loggedStudent;   


    public StudentDashboard(Student s) {
    this.loggedStudent = s;
    initComponents();

    // Hide buttons until selection
    jButton2.setVisible(false);
    jButton4.setVisible(false);

    jList1.addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            String selected = jList1.getSelectedValue();
            jButton2.setVisible(selected != null); 
        }
    });

    jList2.addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            String selected = jList2.getSelectedValue();
            jButton4.setVisible(selected != null); 
        }
    });
}


    public StudentDashboard() {
        initComponents();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("View Avaliable Courses");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("View Enrolled Courses");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        jButton2.setText("Enroll");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setText("View lessons");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jButton4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(120, 120, 120)
                                .addComponent(jButton2))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    if (loggedStudent == null) {
        JOptionPane.showMessageDialog(this, "No logged student found!");
        return;
    }
    refreshAvailableCourses();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    if (loggedStudent == null) {
        JOptionPane.showMessageDialog(this, "No logged student found!");
        return;
    }
    refreshEnrolledCourses();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    if (loggedStudent == null) {
        JOptionPane.showMessageDialog(this, "No logged student found!");
        return;
    }

    String selected = jList1.getSelectedValue();

    if (selected == null) {
        JOptionPane.showMessageDialog(this, "Please select a course first.");
        return;
    }

    // Extract course ID (before the first " - ")
    String courseId = selected.split(" - ")[0];

    if (loggedStudent.getEnrolledCourses().contains(courseId)) {
        JOptionPane.showMessageDialog(this, "You are already enrolled in this course.");
        return;
    }

    // Enroll the student
    loggedStudent.getEnrolledCourses().add(courseId);

    Map<String, Boolean> prog = loggedStudent.getProgress().get(courseId);
    if (prog == null) {
        loggedStudent.getProgress().put(courseId, new HashMap<String, Boolean>());
    }

    // Save updates
    JsonDatabaseManager db = new JsonDatabaseManager();
    db.updateUser(loggedStudent);

    JOptionPane.showMessageDialog(this, "Course enrolled successfully!");

    // Refresh lists: remove from available, add to enrolled
    refreshAvailableCourses();
    refreshEnrolledCourses();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed


    if (loggedStudent == null) {
        JOptionPane.showMessageDialog(this, "No logged student found!");
        return;
    }

    String selected = jList2.getSelectedValue();
    if (selected == null) {
        JOptionPane.showMessageDialog(this, "Please select an enrolled course.");
        return;
    }

    // Extract course ID (before the first " - ")
    String courseId = selected.split(" - ")[0];

    JsonDatabaseManager db = new JsonDatabaseManager();
    List<Course> allCourses = db.loadCourses();

    Course course = null;

    // Find the selected course inside all courses
    for (Course c : allCourses) {
        if (c.getCourseId().equals(courseId)) {
            course = c;
            break;
        }
    }

    if (course == null) {
        JOptionPane.showMessageDialog(this, "Course not found in database!");
        return;
    }

    // Display lessons in jList3
    DefaultListModel<String> model = new DefaultListModel<>();

    for (Lesson lesson : course.getLessons()) {
        model.addElement(lesson.getTitle());
    }

    jList3.setModel(model);


    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentDashboard().setVisible(true);
            }
        });
    }
private void refreshAvailableCourses() {
    JsonDatabaseManager db = new JsonDatabaseManager();
    List<Course> courses = db.loadCourses();

    DefaultListModel<String> model = new DefaultListModel<>();

    for (Course c : courses) {
        if (loggedStudent == null || !loggedStudent.getEnrolledCourses().contains(c.getCourseId())) {
            model.addElement(c.getCourseId() + " - " + c.getTitle());
        }
    }

    jList1.setModel(model);
    jButton2.setVisible(false);
}

private void refreshEnrolledCourses() {
    JsonDatabaseManager db = new JsonDatabaseManager();
    DefaultListModel<String> model = new DefaultListModel<>();

    if (loggedStudent != null) {
        for (String courseId : loggedStudent.getEnrolledCourses()) {
            String title = db.findCourseById(courseId);
            if (title == null) title = "(Missing Course)";
            model.addElement(courseId + " - " + title);
        }
    }

    jList2.setModel(model);
    jButton4.setVisible(false);
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
