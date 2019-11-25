package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("eb1d4fca4f4f6de1756f084e1a49cbde00b798d2");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("2299350", "java_testcourse")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      // new ImmutableMap.Builder<String, String>().build() is the empty set of pair to see all commits without filtering (instead of null)
      // null is wrong value for this constructor
      //System.out.println(commit);
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
