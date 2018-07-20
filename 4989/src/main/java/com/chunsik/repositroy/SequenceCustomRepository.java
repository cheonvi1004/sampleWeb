package com.chunsik.repositroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.chunsik.entity.Sequence;

@Repository
public class SequenceCustomRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	public long getNextSeqId(String key) {
		Query query = new Query(Criteria.where("_id").is(key));

		Update update = new Update();
		update.inc("seq", 1);

		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		Sequence sequence = mongoTemplate.findAndModify(query, update, options, Sequence.class);

		return sequence != null ? sequence.getSeq() : 0;
	}

}
