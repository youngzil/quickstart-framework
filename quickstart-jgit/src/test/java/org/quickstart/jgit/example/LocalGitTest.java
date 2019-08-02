package org.quickstart.jgit.example;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.RefUpdate;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.junit.Test;

/**
 * @author youngzil@163.com
 * @description TODO
 * @createTime 2019-08-02 14:24
 */
public class LocalGitTest {


  @Test
  public void testCreate() throws IOException {

    String repo = "/Users/yangzl/new_repo/.git";

    // 创建一个新仓库
    Repository newlyCreatedRepo = FileRepositoryBuilder.create(new File(repo));
    newlyCreatedRepo.create();

    // 打开一个存在的仓库
    Repository existingRepo = new FileRepositoryBuilder().setGitDir(new File(repo)).build();

    // 无论你的程序是否知道仓库的确切位置，builder 中的那个流畅的 API 都可以提供给它寻找仓库所需所有信息。
    // 它可以使用环境变量 （.readEnvironment()） ，从工作目录的某处开始并搜索 （.setWorkTree(…).findGitDir()） , 或者仅仅只是像上面那样打开一个已知的 .git 目录。

    // 获取引用
    // Ref master = newlyCreatedRepo.getRef("master");
    Ref master = newlyCreatedRepo.exactRef("master");

    // 获取该引用所指向的对象
    ObjectId masterTip = master.getObjectId();

    // Rev-parse
    ObjectId obj = newlyCreatedRepo.resolve("HEAD^{tree}");

    // 装载对象原始内容
    ObjectLoader loader = newlyCreatedRepo.open(masterTip);
    loader.copyTo(System.out);

    // 创建分支
    RefUpdate createBranch1 = newlyCreatedRepo.updateRef("refs/heads/branch1");
    createBranch1.setNewObjectId(masterTip);
    createBranch1.update();

    // 删除分支
    RefUpdate deleteBranch1 = newlyCreatedRepo.updateRef("refs/heads/branch1");
    deleteBranch1.setForceUpdate(true);
    deleteBranch1.delete();

    // 配置
    Config cfg = newlyCreatedRepo.getConfig();
    String name = cfg.getString("user", null, "name");

  }



}
