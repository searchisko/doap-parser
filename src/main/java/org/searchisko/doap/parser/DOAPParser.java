package org.searchisko.doap.parser;

import com.viceversatech.rdfbeans.RDFBeanManager;
import com.viceversatech.rdfbeans.exceptions.RDFBeanException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.ontoware.aifbcommons.collection.ClosableIterator;
import org.ontoware.rdf2go.model.Model;
import org.openrdf.OpenRDFException;
import org.openrdf.query.BindingSet;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.TupleQuery;
import org.openrdf.query.TupleQueryResult;
import org.openrdf.repository.Repository;
import org.openrdf.rdf2go.RepositoryModel;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.sail.SailRepository;
import org.openrdf.rio.RDFFormat;
import org.openrdf.sail.memory.MemoryStore;
import org.searchisko.doap.model.Person;
import org.searchisko.doap.model.Project;

import java.io.File;

/**
 * Created by lukas on 22/09/14.
 */
public class DOAPParser {

	public static Project deserializeFromFile(String localPath) throws RepositoryException {
		Repository rep = new SailRepository(new MemoryStore());
		rep.initialize();
		try {
			File file = new File(localPath);
			RepositoryConnection con = rep.getConnection();

			Model model = new RepositoryModel(rep);
			model.open();
			RDFBeanManager manager = new RDFBeanManager(model);

			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
//			mapper.enable(SerializationConfig.Feature.SORT_PROPERTIES_ALPHABETICALLY );
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
//			mapper.setSerializationInclusion();

			try {
				con.add(file, null, RDFFormat.RDFXML);

				ClosableIterator iter;


				System.out.println("Project ===========");
				iter = manager.getAll(Project.class);
				while (iter.hasNext()) {
					Project p = (Project)iter.next();
					System.out.println(mapper.writeValueAsString(p));
				}



				System.out.println("Persons ===========");
				iter = manager.getAll(Person.class);
				while (iter.hasNext()) {
					Person p = (Person)iter.next();
					System.out.println(mapper.writeValueAsString(p));
				}

			} catch (RDFBeanException e) {
				System.out.println(e);
			} finally {
				model.close();
				con.close();
			}
		} catch (OpenRDFException e) {
			// handle exception
			System.out.println(e);
		} catch (java.io.IOException e) {
			// handle io exception
			System.out.println(e);
		}
		return null;
	}
}
