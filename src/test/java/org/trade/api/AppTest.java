package org.trade.api;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = HttpRest.class, secure = false)
public class AppTest 

{
	   @Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private NodeRepo nodeRepo;
	    
	    @Test
	    public void testGet() throws Exception {
	    	 List<String> res =new ArrayList<String>();
	         res.add("Succesfull GET Mock child Name,ID,height,PID");
	    	 Mockito.when(nodeRepo.getChildrenWitParent(Mockito.anyInt())).thenReturn(res);

	         RequestBuilder requestBuilder = MockMvcRequestBuilders
	                 .get("/getChildren/2")
	                 .accept(MediaType.APPLICATION_JSON);

	         MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	         System.out.println(result.getResponse().getContentAsString());
	         assertEquals("[\"Succesfull GET Mock child Name,ID,height,PID\"]", result.getResponse().getContentAsString());
	    }
	    
	    @Test
	    public void testPost() throws Exception {
	    	
	       	 Mockito.when(nodeRepo.updateParentOfChild(Mockito.anyInt(),Mockito.anyInt())).thenReturn("Update Successful");

	         RequestBuilder requestBuilder = MockMvcRequestBuilders
	                 .post("/changeParent/2/3")
	                 .accept(MediaType.APPLICATION_JSON);

	         MvcResult result = mockMvc.perform(requestBuilder).andReturn();

	         System.out.println(result.getResponse().getContentAsString());
	         assertEquals("Update Successful", result.getResponse().getContentAsString());
	   
	    }
	  
}
