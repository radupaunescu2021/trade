package org.trade.api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class NodeRepo {

	 
	@Autowired 
	JdbcTemplate jdbcTemplate;
	@Autowired
	NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	@Value("${my.table}")
	private String myTable;
	
	private TradeNode toNode(ResultSet resultSet) throws SQLException {
        TradeNode nd = new TradeNode();
        nd.setID(resultSet.getInt("ID"));
        nd.setName(resultSet.getString("name"));
        nd.setPID(resultSet.getInt("PID"));
        nd.setHeight(resultSet.getInt("height"));
        return nd;
    }
 
	
	public List<String> getChildren(String name) {
		List<String> childList =new ArrayList<String>();
		childList.addAll(jdbcTemplate.queryForList("SELECT name from tree;",String.class));
		return childList;
	}
	
	//Get children of a specified node
	public List<String> getChildrenWitParent(int pid) {
		List<String> childList =new ArrayList<String>();
		childList.addAll(namedParamJdbcTemplate.queryForList("SELECT name from "+myTable+" where PID=:pid;",
		 new MapSqlParameterSource("pid", pid),String.class));
		
		return childList;
             
	}
	
	//Change parent of a specified node
	public String updateParentOfChild(int id,int pid) {

		//Get the height of the desired parent node
		namedParamJdbcTemplate.queryForList("SELECT @someHeightVariable:=height from "+myTable+" where ID=:pid;",new MapSqlParameterSource("pid", pid),String.class);
		//UPDATE parent and height
		namedParamJdbcTemplate.update("UPDATE "+myTable+" SET PID = :pid ,height=@someHeightVariable+1 WHERE ID=:id;",
				 new MapSqlParameterSource().addValue("id", id).addValue("pid",pid));
		return "Update successfull \n";
	}
}
