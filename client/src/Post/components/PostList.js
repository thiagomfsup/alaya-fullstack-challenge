import React from 'react';
import PropTypes from 'prop-types';

// Import Components
import PostListItem from './PostListItem';

function PostList(props) {
    if (props.posts) {
        return (
            <div className="d-flex flex-column w-100">
                <h3 className="mt-4">Posts</h3>
                {
                    props.posts.map(post => (
                        <PostListItem
                            post={post}
                            key={post.cuid}
                            onDelete={() => props.handleDeletePost(post.cuid)}
                        />
                    ))
                }
            </div>
        );
    }
    return null;
}

PostList.propTypes = {
    posts: PropTypes.arrayOf(PropTypes.shape({
        name: PropTypes.string.isRequired,
        title: PropTypes.string.isRequired,
        content: PropTypes.string.isRequired,
        slug: PropTypes.string.isRequired,
        cuid: PropTypes.string.isRequired,
        createdBy: PropTypes.string.isRequired
    })).isRequired,
    loggedUser: PropTypes.object.isRequired,
    handleDeletePost: PropTypes.func.isRequired,
};

export default PostList;
