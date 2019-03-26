/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.integration.kafka.offset;

import java.util.Map;

import org.junit.Before;

import org.springframework.integration.kafka.core.Partition;
import org.springframework.integration.kafka.listener.MetadataStoreOffsetManager;
import org.springframework.integration.kafka.listener.OffsetManager;
import org.springframework.integration.metadata.SimpleMetadataStore;

/**
 * @author Marius Bogoevici
 */
public class MetadataStoreOffsetManagerTests extends AbstractOffsetManagerTests {


	// tests share a MetadataStore instance internally, simulating the behaviour of persistent stores
	private SimpleMetadataStore metadataStore;

	@Before
	public void setUp() {
		metadataStore = new SimpleMetadataStore();
	}

	@Override
	protected OffsetManager createOffsetManager(long referenceTimestamp, String consumerId,
			Map<Partition, Long> initialOffsets) throws Exception {
		MetadataStoreOffsetManager metadataStoreOffsetManager;
		metadataStoreOffsetManager = new MetadataStoreOffsetManager(createConnectionFactory(), initialOffsets);
		metadataStoreOffsetManager.setMetadataStore(this.metadataStore);
		metadataStoreOffsetManager.setReferenceTimestamp(referenceTimestamp);
		metadataStoreOffsetManager.setConsumerId(consumerId);
		return metadataStoreOffsetManager;
	}

}