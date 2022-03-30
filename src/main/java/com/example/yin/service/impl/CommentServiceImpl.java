package com.example.yin.service.impl;

import com.example.yin.dao.CommentMapper;
import com.example.yin.dao.RediskvMapper;
import com.example.yin.domain.Comment;
import com.example.yin.service.CommentService;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private RediskvMapper rediskvMapper;

    @Override
    public boolean addComment(Comment comment) {
        rediskvMapper.delete("allcomment");
        int flag=commentMapper.insertSelective(comment);
        Time.sleep(1000);
        return rediskvMapper.delete("allcomment")&&flag>0;
    }

    @Override
    public boolean updateCommentMsg(Comment comment) {
        rediskvMapper.delete("allcomment");
        int flag=commentMapper.updateCommentMsg(comment);
        Time.sleep(1000);
        return rediskvMapper.delete("allcomment")&&flag>0;
    }

//    删除评论
    @Override
    public boolean deleteComment(Integer id) {
        rediskvMapper.delete("allcomment");
        int flag=commentMapper.deleteComment(id);
        Time.sleep(1000);
        return rediskvMapper.delete("allcomment")&&flag>0;
    }

    @Override
    public List<Comment> allComment() {
        if (rediskvMapper.isin("allcomment")){
            return (List<Comment>) rediskvMapper.getStringkv("allcomment");
        }
        List<Comment> listcomment=commentMapper.allComment();
        rediskvMapper.setStringkv("allcomment",listcomment);
        return listcomment;
    }

    @Override
    public List<Comment> commentOfSongId(Integer songId) {
        if (rediskvMapper.isin(songId+"allcomment")){
            return (List<Comment>) rediskvMapper.getStringkv(songId+"allcomments");
        }
        List<Comment> listcommentofsong=commentMapper.commentOfSongId(songId);
        rediskvMapper.setStringkv(songId+"allcomment",listcommentofsong);
        return listcommentofsong;
    }

    @Override
    public List<Comment> commentOfSongListId(Integer songListId) {
        if (rediskvMapper.isin(songListId + "allcommentsl")) {
            return (List<Comment>) rediskvMapper.getStringkv(songListId + "allcommentsl");
        }
        List<Comment> listcommentofsonglist = commentMapper.commentOfSongId(songListId);
        rediskvMapper.setStringkv(songListId + "allcommentsl", listcommentofsonglist);
        return listcommentofsonglist;
    }
}
