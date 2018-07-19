package com.qf.book1.service.impl;
import java.util.List;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.qf.book1.dao.BookDao;
import com.qf.book1.dao.KindDao;
import com.qf.book1.pojo.Book;
import com.qf.book1.pojo.Kind;
import com.qf.book1.pojo.Message;
import com.qf.book1.service.KindService;

/**
 * 图书种类业务逻辑层
 * @author 邓芮
 *
 */
@Service
@Transactional
public class KindServiceImpl implements KindService {
	@Resource
	private KindDao kindDao;
	
	@Resource
	private BookDao bookDao;
	
	@Override
	public List<Kind> findAll() {
		// TODO Auto-generated method stub
		return kindDao.findAll();

	}

	@Override
	public Message addKind(Kind kind) {
		// TODO Auto-generated method stub
		String id = UUID.randomUUID().toString().substring(0, 4);
		kind.setId(id);
		System.out.println("kindadd"+kind.getType());
		Message msg = new Message();
		try {
			if (kind.getType() != null && (!kind.getType().equals(""))) {
			   if((kindDao.findByType(kind.getType())!=null)){
					msg.setMsg("类型已经存在");
					return msg;
			   }		
				kindDao.addKind(kind);
				msg.setMsg("添加成功");
				return msg;
			}
			else{
				msg.setMsg("必须输入类型名字");
				return msg;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			msg.setMsg("系统异常");
			return msg;
		}
	}
	@Override
	public Message updateKind(Kind kind) {
		// TODO Auto-generated method stub
		Message msg=new Message();
		Kind k=kindDao.findByType(kind.getType());
		
		if (k==null) {
			kindDao.updateKind(kind);
			msg.setMsg("修改成功");
			return msg;
		}else{
			msg.setMsg("请核对类型名称重复");
			return msg;
		}	
	}
	@Override
	public Message deleteKind(String kId) {
		// TODO Auto-generated method stub
		Message msg=new Message();
		Kind kind=kindDao.findById(kId);
		if(kind!=null&&!kind.getType().equals("未知")){
//			当id为book的外键时如何删除
		    List<Book> books=bookDao.findByKId(kId);
			System.out.println("asdp,f[a,ewg,ep[mawgpk");

			Kind k=kindDao.findByType("未知");
			if(books!=null){
				for (Book book : books) {
					book.setKind(k);
					bookDao.updateBook(book);
				}
			  }
			kindDao.deleteKind(kind);
			msg.setMsg("删除成功");
			return msg;
			}else{
				msg.setMsg("该对象已被删除或默认种类不能被删除");
				return msg;
		}
	}
}
