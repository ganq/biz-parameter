package com.mysoft.b2b.bizsupport.provider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysoft.b2b.bizsupport.api.QualificationLevel;
import com.mysoft.b2b.bizsupport.api.QualificationLevelService;
import com.mysoft.b2b.bizsupport.mapper.QualificationLevelMapper;

@Service("qualificationLevelService")
public class QualificationLevelServiceImpl implements QualificationLevelService {
	private static final Logger log = Logger.getLogger(QualificationLevelServiceImpl.class);
	
	@Autowired
	private QualificationLevelMapper qLevelMapper;
	
	public List<QualificationLevel> getQualificationLevels(Map<String, Object> param) {
		// TODO Auto-generated method stub
		List<QualificationLevel> qLevels =qLevelMapper.getQualificationLevels(param);
		if(qLevels !=null)
			return qLevels;
		else {
			return new ArrayList<QualificationLevel>();
		}
	}
	
	public int getQualificationLevelsCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return qLevelMapper.getQualificationLevelsCount(param);
	}

	public List<QualificationLevel> getLevelsByQualificationCode(String code) {
		// TODO Auto-generated method stub
		if(code ==null)
		{
			log.error("code参数不能为null", new RuntimeException("code参数不能为null"));
			return null;
		}
		
		List<QualificationLevel> qLevels = qLevelMapper.getLevelsByQualificationCode(code);
		
		if(qLevels !=null)
			return qLevels;
		else 
			return new ArrayList<QualificationLevel>();
		
	}

	public QualificationLevel getQualificationLevelByCode(String code) {
		// TODO Auto-generated method stub
		return qLevelMapper.getQualificationLevelByCode(code);
	}

	public int getLevelsByQualificationCodeCount(String code) {
		// TODO Auto-generated method stub
		return qLevelMapper.getLevelsByQualificationCodeCount(code);
	}

	public boolean addQualificationLevel(QualificationLevel qLevel) {
		// TODO Auto-generated method stub
		return qLevelMapper.addQualificationLevel(qLevel) > 0;
	}

	public boolean updateQualificationLevel(QualificationLevel qLevel) {
		// TODO Auto-generated method stub
		return qLevelMapper.updateQualificationLevel(qLevel) > 0;
	}
	
	public QualificationLevel getQualificationLevelById(String id) {
		// TODO Auto-generated method stub
		return qLevelMapper.getQualificationLevelById(id);
	}
	
	public List<QualificationLevel> getLevelsIncludeUnusedByQCode(
			String qualificationCode) {
		// TODO Auto-generated method stub
		List<QualificationLevel> qLevels = qLevelMapper.getLevelsIncludeUnusedByQCode(qualificationCode);
		if(qLevels !=null)
			return qLevels;
		else 
			return new ArrayList<QualificationLevel>();
	}

	public int compareQualificationLevel(String levelCode1, String levelCode2) {
		// TODO Auto-generated method stub
		QualificationLevel level1 = qLevelMapper.getQualificationLevelByCode(levelCode1);
		QualificationLevel level2 = qLevelMapper.getQualificationLevelByCode(levelCode2);
		if(level1.getPriority() > level2.getPriority())
			return 1;
		else  if(level1.getPriority() == level2.getPriority())
			return 0;
		else if(level1.getPriority() < level2.getPriority())
			return -1;
		return 0;
	}
}
