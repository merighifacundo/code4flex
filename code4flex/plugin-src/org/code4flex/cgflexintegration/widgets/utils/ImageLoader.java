 /*
 * Code4Flex, Generation Code Tool for Flex enviroment
 *
 * Copyright (c) 2008, Knowledgeit or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by KnowledgeIt.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 * Boston, MA  02110-1301  USA
 * 
 * Visit our site: http://knowledgeit.com.ar/code4flex/
 *
 */

package org.code4flex.cgflexintegration.widgets.utils;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;

/**
 * 
 * @author Facundo Merighi
 * @version $Revision: 1.1 $
 */

public class ImageLoader {
	
	private static HashMap<URL, Image> m_URLImageMap = new HashMap<URL, Image>();
	
	private static HashMap<ImageDescriptor, Image> m_DescriptorImageMap = new HashMap<ImageDescriptor, Image>();
	
	public static Image getPluginImage(Object plugin, String name) {
        try {
            try {
                URL url = getPluginImageURL(plugin, name);
                if (m_URLImageMap.containsKey(url))
                    return m_URLImageMap.get(url);
                InputStream is = url.openStream();
                Image image;
                try {
                    image = getImage(is);
                    m_URLImageMap.put(url, image);
                } finally {
                    is.close();
                }
                return image;
            } catch (Throwable e) {
            	// Ignore any exceptions
            }
        } catch (Throwable e) {
        	// Ignore any exceptions
        }
        return null;
    }
	
	private static URL getPluginImageURL(Object plugin, String name) throws Exception {
		// try to work with 'plugin' as with OSGI BundleContext
		try {
			Class<?> bundleClass = Class.forName("org.osgi.framework.Bundle"); //$NON-NLS-1$
			Class<?> bundleContextClass = Class.forName("org.osgi.framework.BundleContext"); //$NON-NLS-1$
			if (bundleContextClass.isAssignableFrom(plugin.getClass())) {
				Method getBundleMethod = bundleContextClass.getMethod("getBundle", new Class[0]); //$NON-NLS-1$
				Object bundle = getBundleMethod.invoke(plugin, new Object[0]);
				//
				Class<?> ipathClass = Class.forName("org.eclipse.core.runtime.IPath"); //$NON-NLS-1$
				Class<?> pathClass = Class.forName("org.eclipse.core.runtime.Path"); //$NON-NLS-1$
				Constructor<?> pathConstructor = pathClass.getConstructor(new Class[]{String.class});
				Object path = pathConstructor.newInstance(new Object[]{name});
				//
				Class<?> platformClass = Class.forName("org.eclipse.core.runtime.Platform"); //$NON-NLS-1$
				Method findMethod = platformClass.getMethod("find", new Class[]{bundleClass, ipathClass}); //$NON-NLS-1$
				return (URL) findMethod.invoke(null, new Object[]{bundle, path});
			}
		} catch (Throwable e) {
        	// Ignore any exceptions
		}
		// else work with 'plugin' as with usual Eclipse plugin
		{
			Class<?> pluginClass = Class.forName("org.eclipse.core.runtime.Plugin"); //$NON-NLS-1$
			if (pluginClass.isAssignableFrom(plugin.getClass())) {
				//
				Class<?> ipathClass = Class.forName("org.eclipse.core.runtime.IPath"); //$NON-NLS-1$
				Class<?> pathClass = Class.forName("org.eclipse.core.runtime.Path"); //$NON-NLS-1$
				Constructor<?> pathConstructor = pathClass.getConstructor(new Class[]{String.class});
				Object path = pathConstructor.newInstance(new Object[]{name});
				//
				Method findMethod = pluginClass.getMethod("find", new Class[]{ipathClass}); //$NON-NLS-1$
				return (URL) findMethod.invoke(plugin, new Object[]{path});
			}
		}
		return null;
	}
	
    public static Image getImage(ImageDescriptor descriptor) {
        if (descriptor == null)
            return null;
        Image image = m_DescriptorImageMap.get(descriptor);
        if (image == null) {
            image = descriptor.createImage();
            m_DescriptorImageMap.put(descriptor, image);
        }
        return image;
    }
    protected static Image getImage(InputStream is) {
        Display display = Display.getCurrent();
        ImageData data = new ImageData(is);
        if (data.transparentPixel > 0)
            return new Image(display, data, data.getTransparencyMask());
        return new Image(display, data);
    }
}
