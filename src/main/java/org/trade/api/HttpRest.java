package org.trade.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpRest {
	@Autowired
	NodeRepo repo;
	
	@GetMapping("/getChildren/{pid}")
	public List<String> getChildren( @PathVariable("pid") int pid) {
		
		//return "getChildren of node " + name;
		return repo.getChildrenWitParent(pid);
		
	}
	
	@PostMapping("/changeParent/{nodeId}/{parentId}")
	public String changeParent(@PathVariable("node") int node,@PathVariable("parent") int parent) {
		//return "change parent of node " + node +"with new parent " + parent;
		return repo.updateParentOfChild(node, parent);
	}
	
	
	

}
