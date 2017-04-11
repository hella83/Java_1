package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

/**
 * Created by Elena on 08.04.2017.
 */
public class SoapTests extends TestBase{

    @Test(enabled = true)
        public void testGetProjects() throws MalformedURLException, ServiceException, RemoteException {
        int issueId= 0000004;
        app.soap().skipIfNotFixed(issueId);
        Set<Project> projects = app.soap().getProjects();
        System.out.println(projects.size());
        for (Project project : projects){
            System.out.println(project.getName());
        }
    }

    @Test(enabled = true)
    public void testCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
        int issueId= 0000002;
        app.soap().skipIfNotFixed(issueId);
        Set<Project> projects = app.soap().getProjects();
        Issue issue = new Issue().withSummary("test summary")
                .withDescription("test desc").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary());
    }

}
