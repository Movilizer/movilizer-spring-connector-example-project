package com.movilizer.connector.example.md;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import com.movilitas.movilizer.v14.MovilizerMasterdataPoolUpdate;
import com.movilizer.connector.MovilizerConnectorAPI;
import com.movilizer.connector.annotations.masterdata.MafAppSpace;
import com.movilizer.connector.annotations.masterdata.MasterdataDescription;
import com.movilizer.connector.annotations.masterdata.MasterdataEntry;
import com.movilizer.connector.annotations.masterdata.MasterdataFilter1;
import com.movilizer.connector.annotations.masterdata.MasterdataGroup;
import com.movilizer.connector.annotations.masterdata.MasterdataKey;
import com.movilizer.connector.annotations.masterdata.MasterdataPool;
import com.movilizer.connector.annotations.masterdata.MasterdataPoolNameGenerator;
import com.movilizer.connector.example.ExcludeExamplesConfiguration;
import com.movilizer.connector.mapper.annotated.MasterdataMapper;

@Configuration
@Import(ExcludeExamplesConfiguration.class)
public class MasterDataSendingConfiguration {

	@MasterdataPool
	public class BusinessObjectMD {

		public static final String name = "POOL";

		@MafAppSpace
		public static final String mafAppSpace = "APP_SPACE_EXAMPLE";

		@MasterdataKey
		String id;

		@MasterdataGroup
		String group;

		@MasterdataDescription
		String description;

		@MasterdataFilter1
		String filter1;

		@MasterdataEntry(name = "entry-1")
		String entry1;

		@MasterdataPoolNameGenerator
		public String getPoolName() {
			return name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getGroup() {
			return group;
		}

		public void setGroup(String group) {
			this.group = group;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getFilter1() {
			return filter1;
		}

		public void setFilter1(String filter1) {
			this.filter1 = filter1;
		}
	}

	@Component
	public class MasterDataSender
	{

		@Autowired
		MovilizerConnectorAPI movilizer;

		@Autowired
		MasterdataMapper mapper;

		@Autowired
		public MasterDataSender()
		{

		}

        @PostConstruct
        void send() {
    		List<BusinessObjectMD> instances = new ArrayList<BusinessObjectMD>();

    		BusinessObjectMD bo = new BusinessObjectMD();
    		bo.setDescription("example-description");
    		bo.setFilter1("example-filter");
    		bo.setGroup("example-group");
    		bo.setId("example-id");
    		instances.add(bo);

    		Collection<MovilizerMasterdataPoolUpdate> updates = mapper.getMasterdata(instances,
    				MasterdataMapper.Action.UPDATE);
    		movilizer.updateMasterdata(updates);
        }
	}

}
