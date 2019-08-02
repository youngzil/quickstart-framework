package org.quickstart.jgit.example;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.AddCommand;
import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.PushCommand;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-02 14:55
 */
public class RemoteGitTest {

  static String localRepoPath = "/Users/yangzl/quickstart-dependencies";
  static String localRepoConfig = "/Users/yangzl/quickstart-dependencies/.git";

  static String initLocalCodeDir = "/Users/yangzl";

  static String remoteRepoUri = "https://github.com/youngzil/quickstart-dependencies.git";
  static String branchName = "v1.0";
  static String gitUsername = "youngzil@163.com";
  static String gitPassword = "yangziliang@123";

  public static void main(String[] args) throws GitAPIException, IOException {

    // 设置远程服务器上的用户名和密码
    UsernamePasswordCredentialsProvider provider = new UsernamePasswordCredentialsProvider(gitUsername, gitPassword);

    Git git = Git.cloneRepository().setURI(remoteRepoUri) // 设置远程URI
        .setBranch("master") // 设置clone下来的分支,默认master
        .setDirectory(new File(localRepoPath)) // 设置下载存放路径
        .setCredentialsProvider(provider) // 设置权限验证
        .call();

    String filePath = "/Users/yangzl/gcviewer.properties";
    String comment = "test jgit";

    // 1、add
    git.add().addFilepattern(".").call();

    // 2、commit 提交
    git.commit().setMessage(comment).call();

    // 3、push 推送到远程
    git.push().setCredentialsProvider(provider).call();

    Iterable<RevCommit> revCommits = git.log().call();
    revCommits.forEach(revCommit -> {
      System.out.println(revCommit.getFullMessage() + " == " + revCommit.getCommitTime());
    });

    System.in.read();
  }

  @Test
  public void testLocal() throws IOException, GitAPIException {

    // 设置远程服务器上的用户名和密码
    UsernamePasswordCredentialsProvider provider = new UsernamePasswordCredentialsProvider(gitUsername, gitPassword);

    Repository existingRepo = new FileRepositoryBuilder().setGitDir(new File(localRepoConfig)).build();
    Git git = new Git(existingRepo);

    AddCommand add = git.add();
    add.addFilepattern(".").call();// git add .

    CommitCommand commit = git.commit();
    /** -Dusername=%teamcity.build.username% **/
    commit.setCommitter("yangzl", "youngzil@163.com");
    commit.setAuthor("yangzl", "youngzil@163.com");
    commit.setAll(true);
    // commit.setCommitter(new PersonIdent(repository));
    RevCommit revCommit = commit.setMessage("use jgit").call();// git commit -m "use jgit"

    String commitId = revCommit.getId().name();
    System.out.println(commitId);

    PushCommand push = git.push();
    push.setCredentialsProvider(provider);
    push.call();// git push

  }

  @Test
  public void test() throws IOException, GitAPIException {
    // 在用户的账号配置了ssh，即可提交
    FileRepositoryBuilder builder = new FileRepositoryBuilder();
    String projectURL = System.getProperty("user.dir");
    Repository repository = builder.setGitDir(new File(projectURL.substring(0, projectURL.lastIndexOf("\\")) + "\\.git")).readEnvironment() // scan environment GIT_* variables
        .findGitDir() // scan up the file system tree
        .build();
    Git git = new Git(repository);
    AddCommand add = git.add();
    add.addFilepattern(".").call();// git add .
    CommitCommand commit = git.commit();
    /** -Dusername=%teamcity.build.username% **/
    commit.setCommitter("yangzl", "youngzil@163.com");
    commit.setAuthor("yangzl", "youngzil@163.com");
    commit.setAll(true);
    // commit.setCommitter(new PersonIdent(repository));
    RevCommit revCommit = commit.setMessage("use jgit").call();// git commit -m "use jgit"
    String commitId = revCommit.getId().name();
    System.out.println(commitId);
    PushCommand push = git.push();
    push.call();// git push
  }

}
