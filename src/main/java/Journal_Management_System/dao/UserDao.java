package main.java.Journal_Management_System.dao;

import main.java.Journal_Management_System.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDao {
    public boolean exists(String username){
        String sql="select * from users where username=?";
        //twr
        //��ȡ���� ��ȡԤ����ִ����
        try(Connection con= DatabaseConnection.getCon();
            PreparedStatement pst=con.prepareStatement(sql);)
        {
            //�������
            pst.setString(1,username);
            //��ȡ�����
            ResultSet rs=pst.executeQuery();
            //�ж��û��治����
            return rs.next();
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public boolean checkUser(String username,String password){
        String sql="select * from f_user where username=? and password=?";
        //twr
        //��ȡ���� ��ȡԤ����ִ����
        try(Connection con= DatabaseConnection.getCon();
            PreparedStatement pst=con.prepareStatement(sql);)
        {
            //�������
            pst.setString(1,username);
            pst.setString(2,password);
            //��ȡ�����
            ResultSet rs=pst.executeQuery();
            //�ж��û��治����
            return rs.next();
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }


    public boolean addUser(String username,String password){
        String sql="insert into users(username,password,role) values (?,?,?)";
        try(Connection con= DatabaseConnection.getCon();
            PreparedStatement pst=con.prepareStatement(sql);)
        {
            pst.setString(1,username);
            pst.setString(2,password);
            pst.setString(3,"user");
            return pst.executeUpdate()==1;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
