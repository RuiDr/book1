package com.qf.book1.dao.impl;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.qf.book1.dao.BookDao;
import com.qf.book1.pojo.Book;
@Repository
public class BookDaoImpl implements BookDao {
	
	@Resource
	private SessionFactory sessionFactory;

	@Override
	public List<Book> findByKId(String kId) {
		String hql="from Book b where b.kind.id=:kId";
		return sessionFactory.getCurrentSession().createQuery(hql, Book.class).setParameter("kId", kId).getResultList();	
		}
	
	@Override
	public void updateBook(Book book) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().merge(book);
	}		
	@Override
	public List<Book> findAll( ) {
		// TODO Auto-generated method stub
		String hql="from Book b order by b.id";
		return sessionFactory.getCurrentSession().createQuery(hql,Book.class).getResultList();
	}

	@Override
	public List<Book> findByLike(String bookName) {
		// TODO Auto-generated method stub
		String hql="from Book b where b.bookName like :bookName";
//		模糊查询
		return sessionFactory.getCurrentSession().createQuery(hql,Book.class).setParameter("bookName", "%"+bookName).getResultList();
	}
	@Override
	public Book findByName(String bookName) {
		// TODO Auto-generated method stub
		String hql="from Book b where b.bookName=:bookName";
		return sessionFactory.getCurrentSession().createQuery(hql,Book.class).setParameter("bookName", bookName).uniqueResult();
	}

	@Override
	public void addBook(Book book) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().persist(book);
	}

	@Override
	public Book findById(String id) {
		// TODO Auto-generated method stub
		String hql="from Book b where b.id=:id";
		return sessionFactory.getCurrentSession().createQuery(hql,Book.class).setParameter("id", id).uniqueResult();
	}

	@Override
	public void deleteBook(Book book) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(book);
	}	
}
