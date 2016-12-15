package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by chesnokova.sa on 15.12.2016.
 */
public class GithubTests {

    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("d6122cbeb5aa8270ad41473c252b97e7dc60e1ac");
        //моя учетная запись и репозиторий
        RepoCommits commits = github.repos().get(new Coordinates.Simple("SvetlanaChesnokova", "java_student")).commits();
       //по мимо списков и множеств есть Map - это асоциативные массивы, карты, наборы пар
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            //System.out.println(commit);
            System.out.println(new RepoCommit.Smart(commit).message());
        }

    }
}
