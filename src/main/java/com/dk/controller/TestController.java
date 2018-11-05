package com.dk.controller;

import com.dk.entity.TestTable;
import com.dk.repository.TestTableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {
	Logger logger = LoggerFactory.getLogger(TestController.class);

	@Resource
	private TestTableRepository testTableRepository;

	@GetMapping("/hello")
	public String test(){
		logger.info("say hello");
		return "Hello World";
	}

	@GetMapping("/findall")
	public Page<TestTable> findAll(){
		Sort sort = new Sort(Sort.Direction.DESC,"lastModifyDate");
		Pageable pageable = new PageRequest(0,10,sort);
		return testTableRepository.findAll(pageable);
	}

	@RequestMapping("/save")
	public List<TestTable> save(){
		TestTable table = new TestTable();

		table.setRenderingEngine("Webkit");
		table.setBrowser("Safari 1.2");
		table.setPlatforms("OSX.3");
		table.setEngineVersion("125.5");
		table.setCssGrade("A");

		testTableRepository.save(table);
		return testTableRepository.findAll();
	}

}
