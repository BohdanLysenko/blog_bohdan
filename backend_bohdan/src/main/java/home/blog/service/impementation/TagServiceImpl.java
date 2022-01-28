package home.blog.service.impementation;


import home.blog.dto.TagDTO;
import home.blog.model.Tag;
import home.blog.repository.TagRepository;
import home.blog.service.TagService;
import home.blog.utils.MappingUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    MappingUtils mappingUtils = new MappingUtils();

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag create(Tag tag)  {
        return tagRepository.save(tag);
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Tag findById(Long id) {
         return tagRepository.findById(id).orElseThrow();
    }

    @Override
    public Tag update(Long id, Tag tag) {
        return null;
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public List<TagDTO> findAllById(Long id) {
        ArrayList<TagDTO> tags = new ArrayList<>(tagRepository.findAll().stream()
                .map(mappingUtils::mapToTagDTO)
                .collect(Collectors.toList()));
        ArrayList<TagDTO> tagsOfAuthor = new ArrayList<>();
        for (TagDTO tagDTO : tags){
            if (tagDTO.getComment_id().equals(id)) {
                tagsOfAuthor.add(tagDTO);
            }
        }
        return tagsOfAuthor;
    }
}
