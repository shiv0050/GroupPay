import React, { useState } from 'react'
import { createPortal } from 'react-dom'
import createCache from '@emotion/cache'
import { CacheProvider } from '@emotion/react'
import styled from 'styled-components'
import ReactDOM from 'react-dom';

const PreviewIframe = styled('iframe')(() => ({
    border: 'none',
    height: '100vh',
    width: '100vw'
}))
export const IFrame = ({
  children,
  ...props
}) => {
    const [contentRef, setContentRef] = useState(null)
    const mountNode = contentRef?.contentWindow?.document?.body
    const cache = createCache({
        key: 'css',
        container: contentRef?.contentWindow?.document?.head,
        prepend: true
    })
    return (
        <PreviewIframe ref={setContentRef}>
            {mountNode &&
                ReactDOM.createPortal(
                    <CacheProvider value={cache}>
                        {children}
                    </CacheProvider>,
                    mountNode
                )}
        </PreviewIframe>
    )
}