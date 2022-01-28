package home.blog.service;

import home.blog.dto.TagDTO;
import home.blog.model.Tag;

import java.util.List;

public interface TagService {
    Tag create(Tag tag);

    boolean delete(Long id);

    Tag findById(Long id);

    Tag update(Long id, Tag tag);

    List<Tag> getAll();

    List<TagDTO> findAllById(Long id);
}
